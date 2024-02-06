package zw.co.afrosoft.SubTask;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zw.co.afrosoft.Requests.SubTaskRequest;
import zw.co.afrosoft.Responses.Response;
import zw.co.afrosoft.subtask.SubTaskService;


@RestController
@RequestMapping("")
public class SubTaskController {

    private  final SubTaskService subTaskService;

    public SubTaskController(SubTaskService subTaskService) {
        this.subTaskService = subTaskService;
    }

    @PostMapping("/createSubTask")
    @Operation (summary = "Create a new SubTask")
    public ResponseEntity<Response> createSubTask(@Valid @RequestBody SubTaskRequest subTaskRequest){
            return subTaskService.createSubTask(subTaskRequest);
    }
    
    @PutMapping("/completeSubtask/{subTaskID}")
    @Operation (summary = "Complete sub task by its ID")
    public ResponseEntity<Response> completeSubTask(@PathVariable("subTaskID") Integer subTaskID){
            return subTaskService.completeSubTask(subTaskID);
    }
    @DeleteMapping("/deleteSubTask/{subTaskID}")
    @Operation (summary = "Delete sub task by its ID")
    public ResponseEntity<Response> deleteSubTask(@PathVariable("subTaskID") Integer subTaskID){
            return subTaskService.deleteSubTask(subTaskID);
    }
}