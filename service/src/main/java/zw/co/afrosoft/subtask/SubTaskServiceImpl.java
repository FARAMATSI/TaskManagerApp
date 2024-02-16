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
    public SubTask createSubTask(SubTaskRequest subTaskRequest){

            SubTask subTask = SubTask.builder()
                    .subTaskName(subTaskRequest.getSubTaskName())
                    .isSubTaskCompleted(Boolean.FALSE)
                    .build();
            var task = new Task();
            task.setId(subTaskRequest.getTaskID());
            subTask.setTask(task);
            subTaskRepository.save(subTask);
            return subTask;
       // return ResponseEntity.ok().body(assign)

    }
    @Override
    public SubTask completeSubTask(Integer subTaskID){
            Optional<SubTask> existingSubTask = subTaskRepository.findById(subTaskID);
            if(existingSubTask.isEmpty()){
                throw new SubTaskNotFoundException("SubTask not found in the Database");
            }
            existingSubTask.get().setIsSubTaskCompleted(true);
            taskService.calculateTaskCompletionPercentage(existingSubTask.get().getTask().getId());
           SubTask subTask = subTaskRepository.save(existingSubTask.get());
            return subTask; // revisit
        }
        @Override
        public void deleteSubTask(Integer subTaskID){
            Optional<SubTask> existingSubTask = subTaskRepository.findById(subTaskID);
            if(existingSubTask.isEmpty()){//isPresent
                throw new SubTaskNotFoundException("SubTask not found in the Database");
            }
              subTaskRepository.delete(existingSubTask.get());

        }
    }