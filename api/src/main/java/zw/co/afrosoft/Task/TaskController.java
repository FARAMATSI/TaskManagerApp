package zw.co.afrosoft.Task;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zw.co.afrosoft.Requests.TaskRequest;
import zw.co.afrosoft.Responses.Response;
import zw.co.afrosoft.Responses.task.TaskResponse;
import zw.co.afrosoft.Responses.task.TasksResponse;
import zw.co.afrosoft.TaskRepository;
import zw.co.afrosoft.task.TaskService;


import java.time.LocalDate;


@RestController
public class TaskController {
    private final TaskService taskService;
    private final TaskRepository taskRepository;

    public TaskController(TaskService taskService, TaskRepository taskRepository) {
        this.taskService = taskService;
        this.taskRepository = taskRepository;
    }

    @PostMapping("/createTask")
    @Operation (summary = "Create a new task")
    public ResponseEntity<Response> createTask(@Valid @RequestBody TaskRequest taskRequest) {
            return taskService.createTask(taskRequest);
    }

    @PutMapping("/updateTaskDeadline/{taskID}")
    @Operation (summary = "update the task deadline by its ID")
    public ResponseEntity<Response> updateTaskDescription(@PathVariable("taskID") Integer taskID, @RequestParam LocalDate taskDeadline) {
        return taskService.updateTaskDescription(taskID,taskDeadline);
    }

    @GetMapping("/getTask/{taskID}")
    @Operation (summary = "Retrieves a single task by its ID and its subtasks")
    public ResponseEntity<TaskResponse> getTaskByID(@PathVariable("taskID") Integer taskId) {
        return taskService.getTaskByID(taskId);
    }

    @GetMapping("/tasks/getAllTasks")
    @Operation (summary = "Retrieves all the tasks and their respective assignee and the subtasks")
    public ResponseEntity<TasksResponse> getAllTasks(){
        return taskService.getAllTasks();
    }

    @PersistenceContext
    private EntityManager entityManager;

    @DeleteMapping("/tasks/deleteTask/{taskID}")
    @Operation (summary = "Delete a task and its subtasks as well")
    public ResponseEntity<Response> deleteTask(@Valid @PathVariable("taskID") Integer taskID){
        return taskService.deleteTaskByID(taskID);
    }
}
