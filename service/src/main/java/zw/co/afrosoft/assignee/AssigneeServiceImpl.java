package zw.co.afrosoft.assignee;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import zw.co.afrosoft.Repositories.Assignee.AssigneeRepository;
import zw.co.afrosoft.Requests.Assignee.AssigneeRequest;
import zw.co.afrosoft.Repositories.SubTask.SubTaskRepository;
import zw.co.afrosoft.Repositories.Task.TaskRepository;
import zw.co.afrosoft.entities.subTask.SubTask;
import zw.co.afrosoft.entities.Task.Task;
import zw.co.afrosoft.exceptions.Assignee.AssigneeNotFoundException;
import zw.co.afrosoft.entities.Assignee.Assignee;
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
    public Assignee createAssignee(AssigneeRequest assigneeRequest) {

            var assignee = Assignee.builder()
                    .name(assigneeRequest.getName())
                    .department(assigneeRequest.getDepartment())
                    .profession(assigneeRequest.getProfession())
                    .build();
           return assigneeRepository.save(assignee);


    }


//    @Override
//    public ResponseEntity<Assignee> createAssignee(AssigneeRequest assigneeRequest) {
//            var assignee = Assignee.builder()
//                    .name(assigneeRequest.getName())
//                    .build();
//            Assignee assign = assigneeRepository.save(assignee);
//            return ResponseEntity.ok().body(assign);
//
//    }

    @PersistenceContext
    private EntityManager entityManager;
    @Transactional
    @Override
    public void deleteAssignee(Integer assigneeID){
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

    }

}