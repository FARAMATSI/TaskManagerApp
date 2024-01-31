package zw.co.afrosoft;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Repository;
import zw.co.afrosoft.model.Assignee;

import java.util.Optional;

@Repository
public interface AssigneeRepository  extends JpaRepository<Assignee,Integer> {
    Optional<Assignee> getAssigneeByAssigneeID(Integer assigneeID);
    Optional<Assignee> deleteAssigneeByAssigneeID(Integer assigneeID);
}
