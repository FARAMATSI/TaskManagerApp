package zw.co.afrosoft.Task;

import io.swagger.v3.oas.annotations.Operation;

import jakarta.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zw.co.afrosoft.Requests.Task.TaskRequest;
import zw.co.afrosoft.Responses.Response;
import zw.co.afrosoft.Responses.tasks.TaskResponse;

import zw.co.afrosoft.entities.Task.Task;
import zw.co.afrosoft.entities.subTask.SubTask;
import zw.co.afrosoft.task.TaskService;


import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping("Tasks")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("")
    @Operation (summary = "Create a new task")
    public ResponseEntity<Response> createTask(@Valid @RequestBody TaskRequest taskRequest) {
         taskService.createTask(taskRequest);
           return ResponseEntity.ok(new Response("success","Task created successfully"));
    }

    @PutMapping("/{taskID}")
    @Operation (summary = "update the task deadline by its ID")
    public ResponseEntity<Response> updateTaskDescription(@PathVariable("taskID") Integer taskID, @RequestParam LocalDate taskDeadline) {
        taskService.updateTaskDescription(taskID,taskDeadline);
        return ResponseEntity.ok(new Response("success","Task updated successfully"));
    }

    @GetMapping("/{taskID}")
    @Operation (summary = "Retrieves a single task by its ID and its subtasks")
    public ResponseEntity<TaskResponse> getTaskByID(@PathVariable("taskID") Integer taskId) {
        var task = taskService.getTaskByID(taskId);
        var taskName = task.getTaskName();
        var completionLevel = task.getTaskCompletionPercentage();
        List<SubTask> subTasks = task.getSubTaskList();
        return ResponseEntity.ok().body(new TaskResponse(taskName,subTasks,completionLevel));
    }
    @GetMapping("")
    @Operation (summary = "Retrieves all the tasks and their respective assignee and the subtasks")
    public Page<Task>  getAllTasks(@PageableDefault Pageable pageable){
        return taskService.getAllTasks(pageable);
    }

    @DeleteMapping("{taskID}")
    @Operation (summary = "Delete a task and its subtasks as well")
    public ResponseEntity<Response> deleteTask(@Valid @PathVariable("taskID") Integer taskID){
         taskService.deleteTaskByID(taskID);
         return ResponseEntity.ok().body(new Response("success","Task has been successfully deleted"));
    }
}
