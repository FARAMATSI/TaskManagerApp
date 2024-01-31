package zw.co.afrosoft;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import zw.co.afrosoft.Requests.SubTaskRequest;
import zw.co.afrosoft.Responses.Response;
import zw.co.afrosoft.model.SubTask;

public interface SubTaskService {
   ResponseEntity<Response> createSubTask(SubTaskRequest subTaskRequest);
    ResponseEntity<Response> completeSubTask(Integer subTaskID);
}
