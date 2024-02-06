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
    private Integer id;

    @Column(name = "subTask_name")
    private String subTaskName;

    @ManyToOne
    @JsonIgnore // Why did you change the previous annotation
    @JoinColumn(name = "task_ID")
    private Task task;

    @Column(name = "is_SubTask_completed")
    private Boolean isSubTaskCompleted;

}
