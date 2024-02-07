package zw.co.afrosoft.Responses.tasks;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import zw.co.afrosoft.entities.Task;

import java.util.List;
@AllArgsConstructor
@Data
@Builder
public class TasksResponse {
    private String status;
    private List<Task> allTasks;
}
