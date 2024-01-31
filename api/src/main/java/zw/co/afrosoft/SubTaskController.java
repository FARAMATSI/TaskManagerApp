package zw.co.afrosoft;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zw.co.afrosoft.Requests.SubTaskRequest;
import zw.co.afrosoft.Responses.Response;
import zw.co.afrosoft.model.SubTask;

@RestController
@RequestMapping("")
public class SubTaskController {

    private  final SubTaskService subTaskService;

    public SubTaskController(SubTaskService subTaskService) {
        this.subTaskService = subTaskService;
    }

    @PostMapping("/createSubTask")
    public ResponseEntity<Response> createSubTask(@Valid @RequestBody SubTaskRequest subTaskRequest){
            return subTaskService.createSubTask(subTaskRequest);
    }
    
    @PutMapping("/completeSubtask/{subTaskID}")
    public ResponseEntity<Response> completeSubTask(@PathVariable("subTaskID") Integer subTaskID){
            return subTaskService.completeSubTask(subTaskID);
    }
    @DeleteMapping("/deleteSubTask/{subTaskID}")
    public ResponseEntity<Response> deleteSubTask(@PathVariable("subTaskID") Integer subTaskID){
            return subTaskService.deleteSubTask(subTaskID);
    }
}