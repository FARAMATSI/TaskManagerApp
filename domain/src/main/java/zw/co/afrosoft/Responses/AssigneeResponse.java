package zw.co.afrosoft.Responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import zw.co.afrosoft.model.Task;

import java.util.List;
@AllArgsConstructor
@Builder
public class AssigneeResponse {
    String assigneeName;
    List<Task> assigneeTasks;
}
