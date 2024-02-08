package zw.co.afrosoft.task;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import zw.co.afrosoft.Requests.Task.TaskRequest;
import zw.co.afrosoft.Responses.Response;
import zw.co.afrosoft.Responses.tasks.TaskResponse;
import zw.co.afrosoft.Responses.tasks.TasksResponse;
import zw.co.afrosoft.entities.Task.Task;

import java.time.LocalDate;

public interface TaskService {
    ResponseEntity<Response> createTask(TaskRequest taskRequest);
    ResponseEntity<Response> updateTaskDescription(Integer taskID, LocalDate deadline);
    ResponseEntity<TaskResponse> getTaskByID(Integer taskID);
    Page<Task> getAllTasks(Pageable pageable);
    ResponseEntity<Response> deleteTaskByID(Integer taskID);
    void calculateTaskCompletionPercentage(Integer taskID);
    ResponseEntity<TasksResponse> getTaskByAssigneeName(String name);
}
