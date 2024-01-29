package zw.co.afrosoft;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zw.co.afrosoft.Requests.AssigneeRequest;
import zw.co.afrosoft.Requests.SubTaskRequest;
import zw.co.afrosoft.Requests.TaskRequest;
import zw.co.afrosoft.Responses.Response;
import zw.co.afrosoft.model.Assignee;
import zw.co.afrosoft.model.SubTask;
import zw.co.afrosoft.model.Task;

@RestController
public class AssigneeController {
    private final AssigneeService assigneeService;

    public AssigneeController(AssigneeService assigneeService) {
        this.assigneeService = assigneeService;
    }

    @PostMapping("/createAssignee")
    public ResponseEntity<Response> createAssignee(@Valid @RequestBody AssigneeRequest assigneeRequest){
        try {
            Assignee assignee = assigneeService.createAssignee(assigneeRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("success", "assignee creation succesful"));
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("failed","Assignee creation failed"));
        }
        }

}
