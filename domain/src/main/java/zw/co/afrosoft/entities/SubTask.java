package zw.co.afrosoft.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@ToString

@Builder
public class SubTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer subTaskID;

    @Column
    private String subTaskName;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "taskID")
    private Task task;

    @Column
    private Boolean isSubTaskCompleted;

}
