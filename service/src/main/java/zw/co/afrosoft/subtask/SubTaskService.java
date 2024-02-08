package zw.co.afrosoft.subtask;

import org.springframework.http.ResponseEntity;
import zw.co.afrosoft.Requests.subTask.SubTaskRequest;

import zw.co.afrosoft.Responses.subTask.subTaskResponse;

public interface SubTaskService {
   ResponseEntity<subTaskResponse> createSubTask(SubTaskRequest subTaskRequest);
    ResponseEntity<subTaskResponse> completeSubTask(Integer subTaskID);
    ResponseEntity<subTaskResponse> deleteSubTask(Integer subTaskID);
}
