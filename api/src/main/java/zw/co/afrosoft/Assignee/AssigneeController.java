package zw.co.afrosoft.Assignee;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zw.co.afrosoft.AssigneeService;
import zw.co.afrosoft.Requests.AssigneeRequest;

import zw.co.afrosoft.Responses.Response;
import zw.co.afrosoft.Responses.TasksResponse;
import zw.co.afrosoft.TaskService;
import zw.co.afrosoft.model.Assignee;


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
    @Operation(summary = "it provides a list of all the assignees in the database") //?
    public List<Assignee> getAllAssignees(){
        return assigneeService.getAllAssignees();
    }

    @GetMapping("/getTaskByAssigneeName/{name}")
    @Operation(summary = "provides a list of all the tasks assigned to te specified user")
    public ResponseEntity<TasksResponse> getTaskByAssigneeName(@PathVariable("name") String name){
        return taskService.getTaskByAssigneeName(name);
    }
    @DeleteMapping("/deleteAssignee/{assigneeID}")
    @Operation(summary = "deletes an assignee from the database")
    public ResponseEntity<Response> deleteAssignee(@PathVariable("assigneeID") Integer assigneeID){
        return assigneeService.deleteAssignee(assigneeID);
    }
}
