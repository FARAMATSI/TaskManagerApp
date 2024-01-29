package zw.co.afrosoft.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.relational.core.sql.In;

@Entity
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name = "subTasks")
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
