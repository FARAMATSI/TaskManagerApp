package zw.co.afrosoft.assignee;

import org.springframework.http.ResponseEntity;
import zw.co.afrosoft.Requests.AssigneeRequest;
import zw.co.afrosoft.Responses.Response;
import zw.co.afrosoft.model.Assignee;

import java.util.List;
public interface AssigneeService {
    ResponseEntity<Response> createAssignee(AssigneeRequest assigneeRequest);
//    ResponseEntity<AssigneeResponse> findAssigneeByName(String assigneeName);
    List<Assignee> getAllAssignees();
    ResponseEntity<Response> deleteAssignee(Integer assigneeID);
}
