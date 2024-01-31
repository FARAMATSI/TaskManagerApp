package zw.co.afrosoft;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zw.co.afrosoft.Requests.AssigneeRequest;
import zw.co.afrosoft.Requests.SubTaskRequest;
import zw.co.afrosoft.Requests.TaskRequest;
import zw.co.afrosoft.Responses.AssigneeResponse;
import zw.co.afrosoft.Responses.Response;
import zw.co.afrosoft.Responses.TasksResponse;
import zw.co.afrosoft.model.Assignee;
import zw.co.afrosoft.model.SubTask;
import zw.co.afrosoft.model.Task;

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
    public ResponseEntity<Response> createAssignee(@Valid @RequestBody AssigneeRequest assigneeRequest){
            return assigneeService.createAssignee(assigneeRequest);
        }
//        @GetMapping("/getAssignee/{assigneeID}")
//        public ResponseEntity<AssigneeResponse> getAssignee(@PathVariable("assigneeID") Integer assigneeID){
//        return assigneeService.getAssignee(assigneeID);
//        }

//        @DeleteMapping("/deleteAssignee/{assigneeID}")
//        public ResponseEntity<Response> deleteAssignee(@PathVariable("assigneeID") Integer assigneeID){
//        return assigneeService.deleteAssignee(assigneeID);
//        }
    @GetMapping("/getAllAssignees")
    public List<Assignee> getAllAssignees(){
        return assigneeService.getAllAssignees();
    }

    @GetMapping("/getTaskByAssigneeName/{name}")
    public ResponseEntity<TasksResponse> getTaskByAssigneeName(@PathVariable("name") String name){
        return taskService.getTaskByAssigneeName(name);
    }
    @DeleteMapping("/deleteAssignee/{assigneeID}")
    public ResponseEntity<Response> deleteAssignee(@PathVariable("assigneeID") Integer assigneeID){
        return assigneeService.deleteAssignee(assigneeID);
    }
}
