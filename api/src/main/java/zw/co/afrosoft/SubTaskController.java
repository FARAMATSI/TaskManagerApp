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

    @PostMapping("/createSubTasks")
    public ResponseEntity<Response> createSubTask(@Valid @RequestBody SubTaskRequest subTaskRequest){
            SubTask subTask = subTaskService.createSubTask(subTaskRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("success","subTask creation successful"));
    }

    @PutMapping("/completeSubtask/{subTaskID}")
    public ResponseEntity<Response> completeSubTask(@PathVariable("subTaskID") Integer subTaskID){
        subTaskService.completeSubTask(subTaskID);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("success","subTask Completed"));
        }
}
