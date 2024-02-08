package zw.co.afrosoft.assignee;

import org.springframework.http.ResponseEntity;
import zw.co.afrosoft.Requests.Assignee.AssigneeRequest;

import zw.co.afrosoft.Responses.assignee.AssigneeResponse;


public interface AssigneeService {
    ResponseEntity<AssigneeResponse> createAssignee(AssigneeRequest assigneeRequest);


    ResponseEntity<AssigneeResponse> deleteAssignee(Integer assigneeID);
}
