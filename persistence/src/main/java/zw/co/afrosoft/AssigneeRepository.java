package zw.co.afrosoft;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zw.co.afrosoft.entities.Assignee;



@Repository
public interface AssigneeRepository  extends JpaRepository<Assignee,Integer> {

    void deleteAssigneeByAssigneeID(Integer assigneeID);
}
