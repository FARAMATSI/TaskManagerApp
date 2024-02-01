package zw.co.afrosoft.task;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import zw.co.afrosoft.AssigneeRepository;
import zw.co.afrosoft.Requests.TaskRequest;
import zw.co.afrosoft.Responses.Response;
import zw.co.afrosoft.Responses.task.TaskResponse;
import zw.co.afrosoft.Responses.task.TasksResponse;
import zw.co.afrosoft.SubTaskRepository;
import zw.co.afrosoft.TaskRepository;
import zw.co.afrosoft.exceptions.NoTaskToDisplayException;
import zw.co.afrosoft.exceptions.TaskNotFoundException;
import zw.co.afrosoft.model.Assignee;
import zw.co.afrosoft.model.SubTask;
import zw.co.afrosoft.model.Task;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
 private final TaskRepository taskRepository;
 private final SubTaskRepository subTaskRepository;
 private final AssigneeRepository assigneeRepository;

    public ResponseEntity<Response> createTask(TaskRequest taskRequest){
     try {
         var task = Task.builder()
                 .taskName(taskRequest.getTaskName())
                 .taskDescription(taskRequest.getTaskDescription())
                 .taskDeadline(taskRequest.getTaskDeadline())
                 .taskCompletionLevel(0.0)
                 .build();
         var assignee = new Assignee();
         assignee.setAssigneeID(taskRequest.getAssigneeID());
         task.setAssignee(assignee);
         taskRepository.save(task);
         return ResponseEntity.ok(new Response("success","task created"));
     }
     catch (Exception e){
         e.printStackTrace();
         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("failed","task creation failed"));
     }
 }

 @Override
    public ResponseEntity<Response> updateTaskDescription(Integer taskID, LocalDate taskDeadline) {
            Optional<Task> existingTask = taskRepository.findById(taskID);
            if(existingTask.isEmpty()){
                throw new TaskNotFoundException("Task Not found in the Database");
            }
            existingTask.get().setTaskDeadline(taskDeadline);
            taskRepository.save(existingTask.get());
            return ResponseEntity.ok(new Response("success","taskDeadline edited"));
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
    public ResponseEntity<Response> calculateTaskCompletionLevel(Integer taskID){
     List<SubTask> subtasksList = subTaskRepository.findByTaskId(taskID);
     double completionLevel;
     double completedTasks=0;
     double totalSubTasks;
     for(SubTask subTask : subtasksList){
         if(subTask.getIsSubTaskCompleted()){
             completedTasks++;
         }
     }
     totalSubTasks = subtasksList.size();
     try {
         completionLevel = (completedTasks / totalSubTasks) * 100;
         Optional<Task> task = taskRepository.findById(taskID);
         if(task.isEmpty()){
             throw new TaskNotFoundException("Task Not found in the Database");
         }
         task.get().setTaskCompletionLevel(completionLevel);
         return ResponseEntity.ok(new Response("success", completionLevel + "% Complete"));
     }
     catch (ArithmeticException e){
         e.printStackTrace();
         return ResponseEntity.ok(new Response("failed","Arithmetic Exception : -- "+e));
     }
    }
    @Override
    public ResponseEntity<TasksResponse> getAllTasks(){
            List<Task> allTasks =  taskRepository.findAll();
            if(allTasks.isEmpty()){
                throw new NoTaskToDisplayException("No tasks to Display");
            }
            return ResponseEntity.ok(new TasksResponse("all tasks",allTasks));
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
