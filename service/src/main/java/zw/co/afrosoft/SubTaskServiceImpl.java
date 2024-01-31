package zw.co.afrosoft;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import zw.co.afrosoft.Requests.SubTaskRequest;
import zw.co.afrosoft.Responses.Response;
import zw.co.afrosoft.exceptions.RecordNotFoundExeption;
import zw.co.afrosoft.model.SubTask;
import zw.co.afrosoft.model.Task;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SubTaskServiceImpl implements SubTaskService {
    public final SubTaskRepository subTaskRepository;
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
            return ResponseEntity.ok(new Response("failed","error creating task "+e));
        }
    }
    public ResponseEntity<Response> completeSubTask(Integer subTaskID){
            Optional<SubTask> existingSubTask = subTaskRepository.findById(subTaskID);
            if(existingSubTask.isEmpty()){
                throw new RecordNotFoundExeption("SubTask not found in the Database");
            }
            existingSubTask.get().setIsSubTaskCompleted(true);
            subTaskRepository.save(existingSubTask.get());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("success","subTask completed"));
        }
    }