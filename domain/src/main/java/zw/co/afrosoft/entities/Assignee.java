package zw.co.afrosoft.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

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

    @Column
    private String name;

  //  @JsonManagedReference
    @OneToMany(mappedBy = "assignee")
    private List<Task> tasks;
}
