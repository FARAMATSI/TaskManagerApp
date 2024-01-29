package zw.co.afrosoft;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import zw.co.afrosoft.Requests.TaskRequest;
import zw.co.afrosoft.model.Assignee;
import zw.co.afrosoft.model.SubTask;
import zw.co.afrosoft.model.Task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
 private final TaskRepository taskRepository;
 private final SubTaskRepository subTaskRepository;

 public Task createTask(TaskRequest taskRequest){
     var task = Task.builder()
             .taskName(taskRequest.getTaskName())
             .taskDescription(taskRequest.getTaskDescription())
             .taskDeadline(taskRequest.getTaskDeadline())
             .taskCompletionLevel(0.0)
             .build();
    var assignee = new Assignee();
    assignee.setAssigneeID(taskRequest.getAssigneeID());
    task.setAssignee(assignee);
     return taskRepository.save(task);
 }

    public Task updateTaskDescription(Integer taskID, LocalDate taskDeadline) {
        try {
            Task existingTask = taskRepository.findById(taskID).orElse(new Task());
            existingTask.setTaskDeadline(taskDeadline);
            return taskRepository.save(existingTask);
        }
        catch(Exception e){
            e.printStackTrace();
            return new Task();
            }
    }

    public List<SubTask> getTaskByID(Integer taskId) {
        if(taskRepository.findById(taskId).isPresent()){
            try {
                List<SubTask> subTasks = subTaskRepository.findByTaskId(taskId);
                Task task = taskRepository.findById(taskId).orElse(new Task());
                return subTasks;
                 }
            catch (Exception e){
                e.printStackTrace();
                List<SubTask> subTasks = new ArrayList<>(){};
                return subTasks;
            }
        }
        else {
            List<SubTask> subTasks = new ArrayList<>(){};
            return subTasks;
        }
    }

    public List<Task> getAllTasks(){
        try {
            List<Task> allTasks =  taskRepository.findAll();
            return allTasks;
        }
        catch (Exception e){
            e.printStackTrace();
            return new ArrayList<>(){};
        }
    }


    @PersistenceContext
    private EntityManager entityManager;


    @Transactional
    public void deleteTaskByID(Integer taskID){
        try {
            // Deleting all the Task's SubTasks
            subTaskRepository.deleteByTaskId(taskID);

            //Now deleting the task
            if(taskRepository.findById(taskID).isPresent()){
                taskRepository.deleteById(taskID);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
