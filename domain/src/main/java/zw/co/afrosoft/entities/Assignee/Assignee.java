package zw.co.afrosoft.entities.Assignee;

import jakarta.persistence.*;
import lombok.*;
import zw.co.afrosoft.entities.Task.Task;

import java.util.List;
@Entity
@Data
@RequiredArgsConstructor
@AllArgsConstructor

@Builder
public class Assignee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer assigneeID;


    private String AssigneeName;

    private String profession;
    private String department;

  //  @JsonManagedReference
    @OneToMany(mappedBy = "assignee")
    private List<Task> tasks;
}
