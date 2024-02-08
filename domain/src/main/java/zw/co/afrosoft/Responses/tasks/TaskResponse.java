package zw.co.afrosoft.Responses.tasks;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import zw.co.afrosoft.entities.subTask.SubTask;

import java.util.List;
@AllArgsConstructor
@Data
@Builder
public class TaskResponse {
    private String taskName;
    private List<SubTask> subTaskList;
    private Double completionPercentage;
}
