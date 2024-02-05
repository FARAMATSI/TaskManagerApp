package zw.co.afrosoft.Assignee;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zw.co.afrosoft.assignee.AssigneeService;
import zw.co.afrosoft.Requests.AssigneeRequest;

import zw.co.afrosoft.Responses.Response;
import zw.co.afrosoft.Responses.task.TasksResponse;
import zw.co.afrosoft.task.TaskService;
import zw.co.afrosoft.exceptions.AssigneeNotFoundException;
import zw.co.afrosoft.entities.Assignee;


import java.util.List;

@RestController
public class AssigneeController {
    private final AssigneeService assigneeService;
    private final TaskService taskService;
    public AssigneeController(AssigneeService assigneeService, TaskService taskService) {
        this.assigneeService = assigneeService;
        this.taskService = taskService;
    }

    @PostMapping("/createAssignee")
    @Operation(summary ="Create a new Assignee")
    public ResponseEntity<Response> createAssignee(@Valid @RequestBody AssigneeRequest assigneeRequest){
            return assigneeService.createAssignee(assigneeRequest);
        }

    @GetMapping("/getAllAssignees")
    @Operation(summary = "it provides a list of all the assignees and their tasks from the database") //?
    public List<Assignee> getAllAssignees(){
        return assigneeService.getAllAssignees();
    }

    @GetMapping("/getTaskByAssigneeName/{name}")
    @Operation(summary = "provides a list of all the tasks and subtasks assigned to te specified assignee")
    public ResponseEntity<TasksResponse> getTaskByAssigneeName(@PathVariable("name") String name){
        return taskService.getTaskByAssigneeName(name);
    }
    @DeleteMapping("/deleteAssignee/{assigneeID}")
    @Operation(summary = "deletes an assignee from the database")
    public ResponseEntity<Response> deleteAssignee(@PathVariable("assigneeID") Integer assigneeID) throws AssigneeNotFoundException {
        return assigneeService.deleteAssignee(assigneeID);
    }
}
