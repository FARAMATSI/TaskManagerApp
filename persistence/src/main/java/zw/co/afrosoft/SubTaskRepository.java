package zw.co.afrosoft;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import zw.co.afrosoft.entities.SubTask;

import java.util.List;

@Repository
public interface SubTaskRepository extends JpaRepository<SubTask,Integer> {
    List<SubTask> findByTaskId(Integer taskId);


   void deleteByTaskId(Integer taskID);
    void deleteById(Integer assigneeId);
    }

