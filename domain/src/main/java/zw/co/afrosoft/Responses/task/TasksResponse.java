package zw.co.afrosoft.Responses.task;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import zw.co.afrosoft.model.Task;

import java.util.List;
@AllArgsConstructor
@Data
@Builder
public class TasksResponse {
    private String status;
    private List<Task> allTasks;
}
