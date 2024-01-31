package zw.co.afrosoft.Responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import zw.co.afrosoft.model.SubTask;

import java.util.List;
@AllArgsConstructor
@Data
@Builder
public class TaskResponse {
    private String taskName;
    private List<SubTask> subTaskList;
}
