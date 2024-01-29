package zw.co.afrosoft.Responses;

import lombok.AllArgsConstructor;
import zw.co.afrosoft.model.SubTask;

import java.util.List;
@AllArgsConstructor
public class TaskResponse {
    private String taskName;
    private List<SubTask> subTaskList;
}
