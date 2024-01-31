package zw.co.afrosoft;

import org.springframework.data.relational.core.sql.In;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import zw.co.afrosoft.Requests.TaskRequest;
import zw.co.afrosoft.Responses.Response;
import zw.co.afrosoft.Responses.TaskResponse;
import zw.co.afrosoft.Responses.TasksResponse;
import zw.co.afrosoft.model.SubTask;
import zw.co.afrosoft.model.Task;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TaskService {
    ResponseEntity<Response> createTask(TaskRequest taskRequest);
    ResponseEntity<Response> updateTaskDescription(Integer taskID, LocalDate deadline);
    ResponseEntity<TaskResponse> getTaskByID(Integer taskID);
    ResponseEntity<TasksResponse> getAllTasks();
    ResponseEntity<Response> deleteTaskByID(Integer taskID);
    ResponseEntity<Response> calculateTaskCompletionLevel(Integer taskID);
    ResponseEntity<TasksResponse> getTaskByAssigneeName(String name);
}
