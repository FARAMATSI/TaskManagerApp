package zw.co.afrosoft;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zw.co.afrosoft.Requests.TaskRequest;
import zw.co.afrosoft.Responses.Response;
import zw.co.afrosoft.Responses.TaskResponse;
import zw.co.afrosoft.Responses.TasksResponse;
import zw.co.afrosoft.model.SubTask;
import zw.co.afrosoft.model.Task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
public class TaskController {
    private final TaskService taskService;
    private final TaskRepository taskRepository;

    public TaskController(TaskService taskService, TaskRepository taskRepository) {
        this.taskService = taskService;
        this.taskRepository = taskRepository;
    }

    @PostMapping("/createTask")
    public ResponseEntity<Response> createTask(@Valid @RequestBody TaskRequest taskRequest) {
            return taskService.createTask(taskRequest);
    }

    @PutMapping("/updateTaskDeadline/{taskID}")
    public ResponseEntity<Response> updateTaskDescription(@PathVariable("taskID") Integer taskID, @RequestParam LocalDate taskDeadline) {
        return taskService.updateTaskDescription(taskID,taskDeadline);
    }

    @GetMapping("/getTask/{taskID}")
    public ResponseEntity<TaskResponse> getTaskByID(@PathVariable("taskID") Integer taskId) {
        return taskService.getTaskByID(taskId);
    }

    @GetMapping("/tasks/getAllTasks")
    public ResponseEntity<TasksResponse> getAllTasks(){
        return taskService.getAllTasks();
    }

    @PersistenceContext
    private EntityManager entityManager;

    @DeleteMapping("/tasks/deleteTask/{taskID}")
    public ResponseEntity<Response> deleteTask(@Valid @PathVariable("taskID") Integer taskID){
        return taskService.deleteTaskByID(taskID);
    }

    @GetMapping("task/calculateCompletionLeve/{taskID}")
    public ResponseEntity<Response> calculateCompletionLevel(@PathVariable Integer taskID){
        return taskService.calculateTaskCompletionLevel(taskID);
    }
}
