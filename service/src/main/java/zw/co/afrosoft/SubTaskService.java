package zw.co.afrosoft;

import org.springframework.stereotype.Service;
import zw.co.afrosoft.Requests.SubTaskRequest;
import zw.co.afrosoft.model.SubTask;
@Service
public interface SubTaskService {
    SubTask createSubTask(SubTaskRequest subTaskRequest);
    SubTask completeSubTask(Integer subTaskID);
}
