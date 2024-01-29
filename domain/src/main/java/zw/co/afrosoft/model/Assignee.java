package zw.co.afrosoft.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
@Entity
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name = "Assignees")
@Builder
public class Assignee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer assigneeID;

    @JsonManagedReference
    @Column(name = "assignee_Name")
    private String name;

    @OneToMany(mappedBy = "assignee")
    private List<Task> tasks;
}
