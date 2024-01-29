package zw.co.afrosoft;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
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
        SubTask subTask = SubTask.builder()
                .subTaskName(subTaskRequest.getSubTaskName())
                .isSubTaskCompleted(Boolean.FALSE)
                .build();
        var task = new Task();
        task.setId(subTaskRequest.getTaskID());
        subTask.setTask(task);
        return subTaskRepository.save(subTask);
    }
}
