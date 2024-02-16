package zw.co.afrosoft.assignee;

import zw.co.afrosoft.Requests.Assignee.AssigneeRequest;


import zw.co.afrosoft.entities.Assignee.Assignee;



public interface AssigneeService {
    Assignee createAssignee(AssigneeRequest assigneeRequest);

    void deleteAssignee(Integer assigneeID);
}
