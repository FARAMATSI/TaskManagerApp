package zw.co.afrosoft;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zw.co.afrosoft.Requests.SubTaskRequest;
import zw.co.afrosoft.Responses.Response;
import zw.co.afrosoft.model.SubTask;

@RestController
public class SubTaskController {

    private  final SubTaskService subTaskService;
    private final SubTaskRepository subTaskRepository;

    public SubTaskController(SubTaskService subTaskService, SubTaskRepository subTaskRepository) {
        this.subTaskService = subTaskService;
        this.subTaskRepository = subTaskRepository;
    }

    @PostMapping("/createSubTask")
    public ResponseEntity<Response> createSubTask(@Valid @RequestBody SubTaskRequest subTaskRequest){
            return subTaskService.createSubTask(subTaskRequest);
    }
    
    @PutMapping("/completeSubtask/{subTaskID}")
    public ResponseEntity<Response> completeSubTask(@PathVariable("subTaskID") Integer subTaskID){
            return subTaskService.completeSubTask(subTaskID);
    }
}
