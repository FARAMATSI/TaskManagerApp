package zw.co.afrosoft;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import zw.co.afrosoft.Requests.SubTaskRequest;
import zw.co.afrosoft.Responses.Response;
import zw.co.afrosoft.exceptions.RecordNotFoundException;
import zw.co.afrosoft.model.SubTask;
import zw.co.afrosoft.model.Task;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SubTaskServiceImpl implements SubTaskService {
    public final SubTaskRepository subTaskRepository;
    private final TaskService taskService;

    @Override
    public ResponseEntity<Response> createSubTask(SubTaskRequest subTaskRequest){
        try {
            SubTask subTask = SubTask.builder()
                    .subTaskName(subTaskRequest.getSubTaskName())
                    .isSubTaskCompleted(Boolean.FALSE)
                    .build();
            var task = new Task();
            task.setId(subTaskRequest.getTaskID());
            subTask.setTask(task);
            subTaskRepository.save(subTask);
            return ResponseEntity.ok(new Response("success","Subtask created"));
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("failed","error creating task "+e));
        }
    }
    @Override
    public ResponseEntity<Response> completeSubTask(Integer subTaskID){
            Optional<SubTask> existingSubTask = subTaskRepository.findById(subTaskID);
            if(existingSubTask.isEmpty()){
                throw new RecordNotFoundException("SubTask not found in the Database");
            }
            existingSubTask.get().setIsSubTaskCompleted(true);
            taskService.calculateTaskCompletionLevel(existingSubTask.get().getTask().getId());
            subTaskRepository.save(existingSubTask.get());
            return ResponseEntity.status(HttpStatus.OK).body(new Response("success","subTask completed"));
        }
        @Override
        public ResponseEntity<Response> deleteSubTask(Integer subTaskID){
            Optional<SubTask> existingSubTask = subTaskRepository.findById(subTaskID);
            if(existingSubTask.isEmpty()){//isPresent
                throw new RecordNotFoundException("SubTask not found in the Database");
            }
            subTaskRepository.delete(existingSubTask.get());
            return ResponseEntity.status(HttpStatus.OK).body(new Response("success","subTask deleted"));
        }
    }