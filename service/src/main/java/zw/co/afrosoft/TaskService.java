package zw.co.afrosoft;

import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Service;
import zw.co.afrosoft.Requests.TaskRequest;
import zw.co.afrosoft.model.SubTask;
import zw.co.afrosoft.model.Task;

import java.time.LocalDate;
import java.util.List;

public interface TaskService {
    Task createTask(TaskRequest taskRequest);
    Task updateTaskDescription(Integer taskID, LocalDate deadline);
    List<SubTask> getTaskByID(Integer taskID);
    List<Task> getAllTasks();
    void deleteTaskByID(Integer taskID);

}
