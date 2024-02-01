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
import zw.co.afrosoft.exceptions.AssigneeNotFoundException;
import zw.co.afrosoft.model.Assignee;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AssigneeServiceImpl implements AssigneeService {
    private final AssigneeRepository assigneeRepository;
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

//    public ResponseEntity<AssigneeResponse> findAssigneeByName(String assigneeName){
//        Optional<Assignee> existingAssignee = assigneeRepository.findAssigneeByName(assigneeName);
//        if(existingAssignee.isEmpty()){
//            throw new RecordNotFoundException("Assignee not found in the database");
//        }
//        AssigneeResponse
//        return ResponseEntity.ok(new AssigneeResponse(existingAssignee.get()));
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