package zw.co.afrosoft.entities.subTask;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import zw.co.afrosoft.entities.Task.Task;

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


    private String subTaskName;

    @ManyToOne
    @JsonIgnore
    private Task task;


    private Boolean isSubTaskCompleted;

}
