package zw.co.afrosoft.task;

import org.springframework.http.ResponseEntity;
import zw.co.afrosoft.Requests.TaskRequest;
import zw.co.afrosoft.Responses.Response;
import zw.co.afrosoft.Responses.tasks.TaskResponse;
import zw.co.afrosoft.Responses.tasks.TasksResponse;

import java.time.LocalDate;

public interface TaskService {
    ResponseEntity<Response> createTask(TaskRequest taskRequest);
    ResponseEntity<Response> updateTaskDescription(Integer taskID, LocalDate deadline);
    ResponseEntity<TaskResponse> getTaskByID(Integer taskID);
    ResponseEntity<TasksResponse> getAllTasks(int offset, int size);
    ResponseEntity<Response> deleteTaskByID(Integer taskID);
    void calculateTaskCompletionLevel(Integer taskID);
    ResponseEntity<TasksResponse> getTaskByAssigneeName(String name);
}
