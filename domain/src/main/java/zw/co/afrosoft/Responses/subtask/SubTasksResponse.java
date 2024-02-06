package zw.co.afrosoft.Responses.subtask;

import zw.co.afrosoft.entities.SubTask;

import java.util.List;

public class SubTasksResponse {
    final List<SubTask> subTasksList;
    public SubTasksResponse(List<SubTask> subTaskList) {
        this.subTasksList = subTaskList;
    }


}
