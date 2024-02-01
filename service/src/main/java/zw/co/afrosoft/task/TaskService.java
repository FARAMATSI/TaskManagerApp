package zw.co.afrosoft.task;

import org.springframework.http.ResponseEntity;
import zw.co.afrosoft.Requests.TaskRequest;
import zw.co.afrosoft.Responses.Response;
import zw.co.afrosoft.Responses.task.TaskResponse;
import zw.co.afrosoft.Responses.task.TasksResponse;

import java.time.LocalDate;

public interface TaskService {
    ResponseEntity<Response> createTask(TaskRequest taskRequest);
    ResponseEntity<Response> updateTaskDescription(Integer taskID, LocalDate deadline);
    ResponseEntity<TaskResponse> getTaskByID(Integer taskID);
    ResponseEntity<TasksResponse> getAllTasks();
    ResponseEntity<Response> deleteTaskByID(Integer taskID);
    ResponseEntity<Response> calculateTaskCompletionLevel(Integer taskID);
    ResponseEntity<TasksResponse> getTaskByAssigneeName(String name);
}
