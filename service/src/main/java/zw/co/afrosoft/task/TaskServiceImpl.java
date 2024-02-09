package zw.co.afrosoft.task;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import zw.co.afrosoft.Requests.Task.TaskRequest;
import zw.co.afrosoft.Responses.Response;
import zw.co.afrosoft.Responses.tasks.TaskResponse;
import zw.co.afrosoft.Responses.tasks.TasksResponse;
import zw.co.afrosoft.Repositories.SubTask.SubTaskRepository;
import zw.co.afrosoft.Repositories.Task.TaskRepository;
import zw.co.afrosoft.exceptions.Task.NoTaskToDisplayException;
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

    public ResponseEntity<Response> createTask(TaskRequest taskRequest) {
        try {
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
            return ResponseEntity.ok(new Response("success", "task created"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("failed", "task creation failed"));

        }
    }

    @Override
    public ResponseEntity<Response> updateTaskDescription(Integer taskID, LocalDate taskDeadline) {
        Optional<Task> existingTask = taskRepository.findById(taskID);
        if (existingTask.isEmpty()) {
            throw new TaskNotFoundException("Task Not found in the Database");
        }
        existingTask.get().setTaskDeadline(taskDeadline);
        taskRepository.save(existingTask.get());
        return ResponseEntity.ok(new Response("success", "taskDeadline edited"));
    }

    @Override
    public ResponseEntity<TaskResponse> getTaskByID(Integer taskId) {
     Optional<Task> existingTask = taskRepository.findById(taskId);
                if(existingTask.isEmpty()){
                    throw new TaskNotFoundException("Task Not found in the Database");
                }
        List<SubTask> subTasks = subTaskRepository.findByTaskId(taskId);

        TaskResponse response = TaskResponse.builder()
                        .taskName(existingTask.get().getTaskName())
                        .subTaskList(subTasks)
                        .completionPercentage(existingTask.get().getTaskCompletionPercentage())
                        .build();
                return ResponseEntity.ok(response);
    }




    public ResponseEntity<TasksResponse> getTaskByAssigneeName(String name){
        List<Task> allTasks =  taskRepository.getTasksByAssignee_Name(name);
        if(allTasks.isEmpty()){
            throw new NoTaskToDisplayException("No tasks to Display");
        }
        TasksResponse tasksResponse = TasksResponse.builder()
                .status("success")
                .allTasks(allTasks)
                .build();
        return ResponseEntity.ok(tasksResponse);
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
    public ResponseEntity<Response> deleteTaskByID(Integer taskID){

        Optional<Task> existingTask = taskRepository.findById(taskID);

        if(existingTask.isEmpty()){
            throw new TaskNotFoundException("Task not found in the database");
        }
            // Deleting all the Task's SubTasks
            subTaskRepository.deleteByTaskId(taskID);

            //Now deleting the task
            taskRepository.deleteById(taskID);
        return ResponseEntity.ok(new Response("success","task deleted"));
    }
}
