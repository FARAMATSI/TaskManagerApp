package zw.co.afrosoft.task;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import zw.co.afrosoft.Requests.Task.TaskRequest;
import zw.co.afrosoft.Responses.Response;
import zw.co.afrosoft.Repositories.SubTask.SubTaskRepository;
import zw.co.afrosoft.Repositories.Task.TaskRepository;
import zw.co.afrosoft.exceptions.Task.TaskNotFoundException;
import zw.co.afrosoft.entities.Assignee.Assignee;
import zw.co.afrosoft.entities.subTask.SubTask;
import zw.co.afrosoft.entities.Task.Task;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final SubTaskRepository subTaskRepository;

    public Task createTask(TaskRequest taskRequest) {

            var task = Task.builder()
                    .taskName(taskRequest.getTaskName())
                    .taskDescription(taskRequest.getTaskDescription())
                    .taskDeadline(taskRequest.getTaskDeadline())
                    .taskCompletionPercentage(0.0)
                    .build();
            var assignee = new Assignee();
            assignee.setAssigneeID(taskRequest.getAssigneeID());
            task.setAssignee(assignee);
            taskRepository.save(task);
            return task;



    }

    @Override
    public Task updateTaskDescription(Integer taskID, LocalDate taskDeadline) {
        Optional<Task> existingTask = taskRepository.findById(taskID);
        if (existingTask.isEmpty()) {
            throw new TaskNotFoundException("Task Not found in the Database");
        }
        existingTask.get().setTaskDeadline(taskDeadline);
        Task task = taskRepository.save(existingTask.get());
        return task ;
    }

    @Override
    public Task getTaskByID(Integer taskId) {
     Optional<Task> existingTask = taskRepository.findById(taskId);
                if(existingTask.isEmpty()){
                    throw new TaskNotFoundException("Task Not found in the Database");
                }
        List<SubTask> subTasks = subTaskRepository.findByTaskId(taskId);

        Task task = Task.builder()
                        .taskName(existingTask.get().getTaskName())
                        .subTaskList(subTasks)
                        .taskCompletionPercentage(existingTask.get().getTaskCompletionPercentage())
                        .build();
                return task;
    }

    public Page<Task> getTaskByAssigneeName(Pageable pageable,String AssigneeName ) {  //possible logic error
        return taskRepository.findAll(pageable);
    }

    @Override
    public void calculateTaskCompletionPercentage(Integer taskID){
     List<SubTask> subtasksList = subTaskRepository.findByTaskId(taskID);
     double completionPercentage;
     double completedTasks=0;
     double totalSubTasks;

     for(SubTask subTask : subtasksList){
         if(subTask.getIsSubTaskCompleted()){
             completedTasks++;
         }
     }
     totalSubTasks = subtasksList.size();
     try {
         completionPercentage = (completedTasks / totalSubTasks) * 100;

         Optional<Task> task = taskRepository.findById(taskID);
         if(task.isEmpty()){
             throw new TaskNotFoundException("Task Not found in the Database");
         }
         task.get().setTaskCompletionPercentage(completionPercentage);
         ResponseEntity.ok(new Response("success", completionPercentage + "% Complete"));
     }
     catch (ArithmeticException e){
         ResponseEntity.ok(new Response("failed", "Arithmetic Exception : -- " + e.getMessage()));
     }
    }
    @Override
    public Page<Task> getAllTasks(Pageable pageable){
            return   taskRepository.findAll(pageable);


    }
    @PersistenceContext
    private EntityManager entityManager;
    @Transactional
    @Override
    public void deleteTaskByID(Integer taskID){

        Optional<Task> existingTask = taskRepository.findById(taskID);

        if(existingTask.isEmpty()){
            throw new TaskNotFoundException("Task not found in the database");
        }
            // Deleting all the Task's SubTasks
            subTaskRepository.deleteByTaskId(taskID);

            //Now deleting the task
            taskRepository.deleteById(taskID);

    }
}
