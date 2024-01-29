package zw.co.afrosoft;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Repository;
import zw.co.afrosoft.model.Task;
@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {

}
