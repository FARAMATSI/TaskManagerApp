package zw.co.afrosoft;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zw.co.afrosoft.entities.Task;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
    List<Task> getTasksByAssignee_Name(String name);
}
