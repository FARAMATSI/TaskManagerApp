package zw.co.afrosoft.entities.Task;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import zw.co.afrosoft.entities.Assignee.Assignee;
import zw.co.afrosoft.entities.subTask.SubTask;


import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
@ToString

public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    private String taskName;


    private String taskDescription;

    @JsonBackReference
    @ManyToOne
    private Assignee assignee;

    @OneToMany(mappedBy = "task")
    private List<SubTask> subTaskList;


    private Double taskCompletionPercentage;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate taskDeadline;

    private String randomChar;

}
