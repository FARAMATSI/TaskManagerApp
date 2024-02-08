package zw.co.afrosoft.subtask;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import zw.co.afrosoft.Requests.subTask.SubTaskRequest;
import zw.co.afrosoft.Responses.subTask.subTaskResponse;
import zw.co.afrosoft.Repositories.SubTask.SubTaskRepository;
import zw.co.afrosoft.task.TaskService;
import zw.co.afrosoft.exceptions.SubTask.SubTaskNotFoundException;
import zw.co.afrosoft.entities.subTask.SubTask;
import zw.co.afrosoft.entities.Task.Task;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SubTaskServiceImpl implements SubTaskService {
    public final SubTaskRepository subTaskRepository;
    private final TaskService taskService;

    @Override
    public ResponseEntity<subTaskResponse> createSubTask(SubTaskRequest subTaskRequest){
        try {
            SubTask subTask = SubTask.builder()
                    .subTaskName(subTaskRequest.getSubTaskName())
                    .isSubTaskCompleted(Boolean.FALSE)
                    .build();
            var task = new Task();
            task.setId(subTaskRequest.getTaskID());
            subTask.setTask(task);
            subTaskRepository.save(subTask);
            return ResponseEntity.status(HttpStatus.OK).body(new subTaskResponse("success","subTask created"));
        }
        catch (Exception e){

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new subTaskResponse("failed","error creating task "+e.getMessage()));
        }
    }
    @Override
    public ResponseEntity<subTaskResponse> completeSubTask(Integer subTaskID){
            Optional<SubTask> existingSubTask = subTaskRepository.findById(subTaskID);
            if(existingSubTask.isEmpty()){
                throw new SubTaskNotFoundException("SubTask not found in the Database");
            }
            existingSubTask.get().setIsSubTaskCompleted(true);
            taskService.calculateTaskCompletionPercentage(existingSubTask.get().getTask().getId());
            subTaskRepository.save(existingSubTask.get());
            return ResponseEntity.status(HttpStatus.OK).body(new subTaskResponse("success","subTask completed"));
        }
        @Override
        public ResponseEntity<subTaskResponse> deleteSubTask(Integer subTaskID){
            Optional<SubTask> existingSubTask = subTaskRepository.findById(subTaskID);
            if(existingSubTask.isEmpty()){//isPresent
                throw new SubTaskNotFoundException("SubTask not found in the Database");
            }
            subTaskRepository.delete(existingSubTask.get());
            return ResponseEntity.status(HttpStatus.OK).body(new subTaskResponse("success","subTask deleted"));
        }
    }