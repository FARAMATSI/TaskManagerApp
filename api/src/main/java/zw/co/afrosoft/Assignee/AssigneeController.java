package zw.co.afrosoft.Assignee;

import io.swagger.v3.oas.annotations.Operation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import zw.co.afrosoft.Responses.assignee.AssigneeResponse;
import zw.co.afrosoft.assignee.AssigneeService;
import zw.co.afrosoft.Requests.Assignee.AssigneeRequest;

import zw.co.afrosoft.Responses.tasks.TasksResponse;
import zw.co.afrosoft.task.TaskService;
import zw.co.afrosoft.exceptions.Assignee.AssigneeNotFoundException;





@RestController
@RequestMapping("/Assignees")
public class AssigneeController {
    private final AssigneeService assigneeService;
    private final TaskService taskService;
    public AssigneeController(AssigneeService assigneeService, TaskService taskService) {
        this.assigneeService = assigneeService;
        this.taskService = taskService;
    }

    @PostMapping("/createAssignee")
    @Operation(summary ="Create a new Assignee")
    public ResponseEntity<AssigneeResponse> createAssignee(@RequestBody AssigneeRequest assigneeRequest){
            return assigneeService.createAssignee(assigneeRequest);
        }


    @GetMapping("/getAssignee/{name}")
    @Operation(summary = "provides a list of all the tasks and subtasks assigned to te specified assignee")
    public ResponseEntity<TasksResponse> getAssigneeByAssigneeName(@PathVariable("name") String name){
        return taskService.getTaskByAssigneeName(name);
    }
    @DeleteMapping("/deleteAssignee/{assigneeID}")
    @Operation(summary = "deletes an assignee from the database")
    public ResponseEntity<AssigneeResponse> deleteAssignee(@PathVariable("assigneeID") Integer assigneeID) throws AssigneeNotFoundException {
        return assigneeService.deleteAssignee(assigneeID);
    }
}
