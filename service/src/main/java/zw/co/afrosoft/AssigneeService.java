package zw.co.afrosoft;

import zw.co.afrosoft.Requests.AssigneeRequest;
import zw.co.afrosoft.model.Assignee;

public interface AssigneeService {
    Assignee createAssignee(AssigneeRequest assigneeRequest);
}
