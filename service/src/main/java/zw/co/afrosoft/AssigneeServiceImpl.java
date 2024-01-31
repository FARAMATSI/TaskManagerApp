package zw.co.afrosoft;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import zw.co.afrosoft.Requests.AssigneeRequest;
import zw.co.afrosoft.Responses.AssigneeResponse;
import zw.co.afrosoft.Responses.Response;
import zw.co.afrosoft.exceptions.RecordNotFoundExeption;
import zw.co.afrosoft.model.Assignee;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AssigneeServiceImpl implements AssigneeService {
    private final AssigneeRepository assigneeRepository;
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
            e.printStackTrace();
            return ResponseEntity.ok(new Response("failed!!!","failed to create assignee {"+e+"}"));
        }
    }
//    @Override
//    public ResponseEntity<AssigneeResponse> getAssignee(Integer assigneeID){
//        Optional<Assignee> existingAssignee = assigneeRepository.getAssigneeByAssigneeID(assigneeID);
//        if(existingAssignee.isEmpty()){
//            throw new RecordNotFoundExeption("Assignee not found in the database");
//        }
//            return ResponseEntity.ok(new AssigneeResponse(existingAssignee.get().getName(),taskRepository.getTaskByAssigneeID(assigneeID)));
//    }
    @Override
    public List<Assignee> getAllAssignees(){
        return assigneeRepository.findAll();
    }
    @Override
    public ResponseEntity<Response> deleteAssignee(Integer assigneeID){
        Optional<Assignee> existingAssignee = assigneeRepository.findById(assigneeID);
        if(existingAssignee.isEmpty()){
            throw new RecordNotFoundExeption("Assignee not found in the database");
        }
        assigneeRepository.deleteAssigneeByAssigneeID(assigneeID);
        return ResponseEntity.ok(new Response("success","assignee deletion successful"));
    }
}