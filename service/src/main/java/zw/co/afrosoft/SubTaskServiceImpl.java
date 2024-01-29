package zw.co.afrosoft;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import zw.co.afrosoft.Requests.SubTaskRequest;
import zw.co.afrosoft.model.Assignee;
import zw.co.afrosoft.model.SubTask;
import zw.co.afrosoft.model.Task;

@Service
@RequiredArgsConstructor
public class SubTaskServiceImpl implements SubTaskService {
    public final SubTaskRepository subTaskRepository;
    @Override
    public SubTask createSubTask(SubTaskRequest subTaskRequest){
        try {
            SubTask subTask = SubTask.builder()
                    .subTaskName(subTaskRequest.getSubTaskName())
                    .isSubTaskCompleted(Boolean.FALSE)
                    .build();
            var task = new Task();
            task.setId(subTaskRequest.getTaskID());
            subTask.setTask(task);
            return subTaskRepository.save(subTask);
        }
        catch (Exception e){
            e.printStackTrace();
            return new SubTask();
        }
    }

    public SubTask completeSubTask(Integer subTaskID){
        try{
            SubTask existingSubTask = subTaskRepository.findById(subTaskID).orElse(new SubTask());
            existingSubTask.setIsSubTaskCompleted(true);
            return subTaskRepository.save(existingSubTask);
        }
        catch (Exception e){
            e.printStackTrace();
            return new SubTask();
        }
    }
}
