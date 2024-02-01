package zw.co.afrosoft.Responses.subtask;

import zw.co.afrosoft.model.SubTask;

import java.util.List;

public class SubTasksResponse {
    private List<SubTask> subTasksList;
    public SubTasksResponse(List<SubTask> subTaskList) {
        this.subTasksList = subTaskList;
    }


}
