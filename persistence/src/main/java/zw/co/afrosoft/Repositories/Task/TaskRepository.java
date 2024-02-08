package zw.co.afrosoft.Repositories.Task;


//import jakarta.annotation.Nullable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import zw.co.afrosoft.entities.Task.Task;

import java.util.List;

@Repository
//@Nullable
public interface TaskRepository extends JpaRepository<Task, Integer> {
    List<Task> getTasksByAssignee_Name(String name);


    void deleteById(@NonNull Integer assigneeId);
}