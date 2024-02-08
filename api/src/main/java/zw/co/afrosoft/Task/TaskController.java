package zw.co.afrosoft.Task;

import io.swagger.v3.oas.annotations.Operation;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zw.co.afrosoft.Requests.Task.TaskRequest;
import zw.co.afrosoft.Responses.Response;
import zw.co.afrosoft.Responses.tasks.TaskResponse;
import zw.co.afrosoft.Responses.tasks.TasksResponse;

import zw.co.afrosoft.task.TaskService;


import java.time.LocalDate;


@RestController
@RequestMapping("Tasks")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
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
    public ResponseEntity<TasksResponse> getAllTasks(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "5") int pageSize){
        return taskService.getAllTasks(pageNumber, pageSize);
    }

    @DeleteMapping("/tasks/deleteTask/{taskID}")
    @Operation (summary = "Delete a task and its subtasks as well")
    public ResponseEntity<Response> deleteTask(@Valid @PathVariable("taskID") Integer taskID){
        return taskService.deleteTaskByID(taskID);
    }
}
