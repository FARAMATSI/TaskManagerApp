package zw.co.afrosoft.Responses.assignee;

import lombok.AllArgsConstructor;
import lombok.Builder;
import zw.co.afrosoft.entities.Task;

import java.util.List;
@AllArgsConstructor
@Builder
public class AssigneeResponse {
    String assigneeName;
    List<Task> assigneeTasks;
}
