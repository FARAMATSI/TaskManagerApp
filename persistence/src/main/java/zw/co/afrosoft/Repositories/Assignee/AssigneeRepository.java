package zw.co.afrosoft.Repositories.Assignee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zw.co.afrosoft.entities.Assignee.Assignee;



@Repository
public interface AssigneeRepository  extends JpaRepository<Assignee,Integer> {


}