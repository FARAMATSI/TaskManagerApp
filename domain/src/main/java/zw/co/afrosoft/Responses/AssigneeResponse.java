package zw.co.afrosoft.Responses;

import lombok.AllArgsConstructor;
import zw.co.afrosoft.model.Task;

import java.util.List;
@AllArgsConstructor
public class AssigneeResponse {
    String assigneeName;
    List<Task> assigneeTasks;
}
