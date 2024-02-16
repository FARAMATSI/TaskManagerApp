package zw.co.afrosoft.subtask;

import org.springframework.http.ResponseEntity;
import zw.co.afrosoft.Requests.subTask.SubTaskRequest;

import zw.co.afrosoft.Responses.subTask.subTaskResponse;
import zw.co.afrosoft.entities.subTask.SubTask;

public interface SubTaskService {
   SubTask createSubTask(SubTaskRequest subTaskRequest);
    SubTask completeSubTask(Integer subTaskID);
    void deleteSubTask(Integer subTaskID);
}
