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


    private String subTaskName;

    @ManyToOne
    @JsonIgnore

    private Task task;


    private Boolean isSubTaskCompleted;

}
