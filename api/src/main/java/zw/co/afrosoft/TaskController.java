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
        try {
            Task task = taskService.createTask(taskRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("success", "task creation successful"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("failed", "Assignee creation failed"));
        }
    }

    @PutMapping("/updateTaskDeadline/{taskID}")
    public ResponseEntity<Response> updateTaskDescription(@PathVariable("taskID") Integer taskID, @RequestParam LocalDate taskDeadline) {
        taskService.updateTaskDescription(taskID,taskDeadline);
        return ResponseEntity.status(HttpStatus.OK).body(new Response("success","task sucesfully updated"));
        }

    @GetMapping("/getTask/{taskID}")
    public ResponseEntity<TaskResponse> getTaskByID(@PathVariable("taskID") Integer taskId) {
        Task task = taskRepository.findById(taskId).orElse(new Task());
        return ResponseEntity.status(HttpStatus.OK).body(new TaskResponse(task.getTaskName(),taskService.getTaskByID(taskId)));
    }

    @GetMapping("/tasks/getAllTasks")
    public ResponseEntity<TasksResponse> getAllTasks(){
        return ResponseEntity.status(HttpStatus.OK).body(new TasksResponse(taskService.getAllTasks()));
    }

    @PersistenceContext
    private EntityManager entityManager;

    @DeleteMapping("/tasks/deleteTask/{taskID}")
    public ResponseEntity<Response> deleteTask(@Valid @PathVariable("taskID") Integer taskID){
        return ResponseEntity.status(HttpStatus.OK).body(new Response("success","task deleted successfully"));
    }

}
