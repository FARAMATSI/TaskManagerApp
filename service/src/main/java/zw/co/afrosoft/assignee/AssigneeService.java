package zw.co.afrosoft.assignee;

import org.springframework.http.ResponseEntity;
import zw.co.afrosoft.Requests.AssigneeRequest;

import zw.co.afrosoft.Responses.assignee.AssigneeResponse;
import zw.co.afrosoft.entities.Assignee;


public interface AssigneeService {
    ResponseEntity<Assignee> createAssignee(AssigneeRequest assigneeRequest);


    ResponseEntity<AssigneeResponse> deleteAssignee(Integer assigneeID);
}
