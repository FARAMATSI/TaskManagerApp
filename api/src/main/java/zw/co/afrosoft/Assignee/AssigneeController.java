package zw.co.afrosoft.Assignee;

import io.swagger.v3.oas.annotations.Operation;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import zw.co.afrosoft.Responses.assignee.AssigneeResponse;
import zw.co.afrosoft.Requests.Assignee.AssigneeRequest;
import zw.co.afrosoft.assignee.AssigneeService;
import zw.co.afrosoft.entities.Task.Task;

import zw.co.afrosoft.task.TaskService;
import zw.co.afrosoft.exceptions.Assignee.AssigneeNotFoundException;





@RestController
@RequestMapping("/assignees")
public class AssigneeController {
    private final AssigneeService assigneeService;
    private final TaskService taskService;
    public AssigneeController(AssigneeService assigneeService, TaskService taskService) {
        this.assigneeService = assigneeService;
        this.taskService = taskService;
    }

    @PostMapping("")
    @Operation(summary ="Create a new Assignee")
    public ResponseEntity<AssigneeResponse> createAssignee(@RequestBody AssigneeRequest assigneeRequest){
             assigneeService.createAssignee(assigneeRequest);
             return ResponseEntity.ok(new AssigneeResponse("success","Assignee created successfully"));
    }


    @GetMapping("/{Assigneename}")
    @Operation(summary = "provides a list of all the tasks and subtasks assigned to te specified assignee")
    public Page<Task> getTaskByAssigneeName(@PageableDefault Pageable pageable, @PathVariable("name") String AssigneeName){
        return taskService.getTaskByAssigneeName(pageable, AssigneeName);

    }

    @DeleteMapping("/{assigneeId}")
    @Operation(summary = "deletes an assignee from the database")
    public ResponseEntity<AssigneeResponse> deleteAssignee(@PathVariable("assigneeId") Integer assigneeID) throws AssigneeNotFoundException {
        assigneeService.deleteAssignee(assigneeID);
        return ResponseEntity.ok(new AssigneeResponse("success","Assignee deleted successfully"));
    }
}
