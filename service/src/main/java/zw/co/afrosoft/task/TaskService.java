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
    Task createTask(TaskRequest taskRequest);
    Task updateTaskDescription(Integer taskID, LocalDate deadline);
    Task getTaskByID(Integer taskID);
    Page<Task> getAllTasks(Pageable pageable);
    void deleteTaskByID(Integer taskID);

    Page<Task> getTaskByAssigneeName(Pageable pageable, String AssigneeName);

    void calculateTaskCompletionPercentage(Integer taskID);

}
