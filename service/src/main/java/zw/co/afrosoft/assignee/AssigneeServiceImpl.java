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
import zw.co.afrosoft.entities.Task;
import zw.co.afrosoft.exceptions.AssigneeNotFoundException;
import zw.co.afrosoft.entities.Assignee;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class AssigneeServiceImpl implements AssigneeService {
    private final SubTaskRepository subTaskRepository;
    private static final Logger LOGGER = Logger.getLogger(AssigneeServiceImpl.class.getName());
    private final AssigneeRepository assigneeRepository;
    @Override
    public ResponseEntity<Response> createAssignee(AssigneeRequest assigneeRequest) {
        try {
            var assignee = Assignee.builder()
                    .name(assigneeRequest.getName())
                    .build();
            assigneeRepository.save(assignee);
            return ResponseEntity.ok(new Response("success","hj"));
        } catch (Exception e) {
            LOGGER.severe("Failed to create assignee: " + e);
            return ResponseEntity.ok(new Response("failed!!!","failed to create assignee {"+e+"}"));
        }
    }

//    @Override
//    public ResponseEntity<AssigneeResponse> createAssignee(AssigneeRequest assigneeRequest) {
//        try {
//            var assignee = Assignee.builder()
//                    .name(assigneeRequest.getName())
//                    .build();
//            assigneeRepository.save(assignee);
//            List<Task> assigneeTasks = new ArrayList<>();
//            Task task1 = new Task(); // Initialize your Task object here
//            Task task2 = new Task(); // Initialize another Task object here
//            assigneeTasks.add(task1);
//            assigneeTasks.add(task2);
//
//            return ResponseEntity.ok(new AssigneeResponse(assignee.getName(), assigneeTasks)); //line 1
//        } catch (Exception e) {
//            List<Task> assigneeTasks = new ArrayList<>();
//            Task task1 = new Task(); // Initialize your Task object here
//            Task task2 = new Task(); // Initialize another Task object here
//            assigneeTasks.add(task1);
//            assigneeTasks.add(task2);
//             LOGGER.severe("Failed to create assignee: " + e);
//             return ResponseEntity.ok(new AssigneeResponse("failed", assigneeTasks));
//        }
//    }


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

        assigneeRepository.deleteAssigneeByAssigneeID(assigneeID);
        return ResponseEntity.ok(new Response("success","assignee deletion successful"));
    }
}