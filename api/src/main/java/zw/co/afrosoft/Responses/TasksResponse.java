package zw.co.afrosoft.Responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import zw.co.afrosoft.model.Task;

import java.util.List;
@AllArgsConstructor
@Data
public class TasksResponse {
    private List<Task> allTasks;
}
