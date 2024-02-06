package zw.co.afrosoft.subtask;

import org.springframework.http.ResponseEntity;
import zw.co.afrosoft.Requests.SubTaskRequest;
import zw.co.afrosoft.Responses.Response;

public interface SubTaskService {
   ResponseEntity<Response> createSubTask(SubTaskRequest subTaskRequest);
    ResponseEntity<Response> completeSubTask(Integer subTaskID);
    ResponseEntity<Response> deleteSubTask(Integer subTaskID);
}
