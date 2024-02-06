package zw.co.afrosoft.assignee;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import zw.co.afrosoft.AssigneeRepository;
import zw.co.afrosoft.Requests.AssigneeRequest;
import zw.co.afrosoft.Responses.Response;
import zw.co.afrosoft.SubTaskRepository;
import zw.co.afrosoft.TaskRepository;
import zw.co.afrosoft.entities.SubTask;
import zw.co.afrosoft.entities.Task;
import zw.co.afrosoft.exceptions.AssigneeNotFoundException;
import zw.co.afrosoft.entities.Assignee;
import zw.co.afrosoft.task.TaskService;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class AssigneeServiceImpl implements AssigneeService {

    private static final Logger LOGGER = Logger.getLogger(AssigneeServiceImpl.class.getName());
    private final AssigneeRepository assigneeRepository;
    private final SubTaskRepository subTaskRepository;
    private final TaskRepository taskRepository;
    @Override
    public ResponseEntity<Response> createAssignee(AssigneeRequest assigneeRequest) {
        try {
            var assignee = Assignee.builder()
                    .name(assigneeRequest.getName())
                    .build();
            assigneeRepository.save(assignee);
            return ResponseEntity.ok(new Response("success","assignee created"));
        } catch (Exception e) {
            LOGGER.severe("Failed to create assignee: " + e);
            return ResponseEntity.ok(new Response("failed!!!","failed to create assignee {"+e+"}"));
        }
    }

    @Override
    public List<Assignee> getAllAssignees(){
        return assigneeRepository.findAll();
    }
    @PersistenceContext
    private EntityManager entityManager;
    @Transactional
    @Override
    public ResponseEntity<Response> deleteAssignee(Integer assigneeID){
        Optional<Assignee> existingAssignee = assigneeRepository.findById(assigneeID);
        if(existingAssignee.isEmpty()){
            throw new AssigneeNotFoundException("Assignee not found in the database");
        }

        Assignee assignee = existingAssignee.get();

        // Deleting the assignee's tasks and their subtasks
        for(Task task : taskRepository.findAll()){
            if(task.getAssignee().getAssigneeID().equals(assigneeID)){
                // Deleting the task's subtasks
                for(SubTask subTask : subTaskRepository.findAll()){
                    if(subTask.getTask().getId().equals(task.getId())){
                        subTaskRepository.delete(subTask);
                    }
                }
                // Deleting the task
                taskRepository.delete(task);
            }
        }

        // Deleting the assignee
        assigneeRepository.delete(assignee);
        return ResponseEntity.ok(new Response("success","assignee deletion successful"));
    }

}