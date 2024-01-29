package zw.co.afrosoft.Responses;

import zw.co.afrosoft.model.SubTask;

import java.util.List;

public class SubTasksResponse {
    private List<SubTask> subTasksList;
    public SubTasksResponse(List<SubTask> subTaskList) {
        this.subTasksList = subTaskList;
    }


}
