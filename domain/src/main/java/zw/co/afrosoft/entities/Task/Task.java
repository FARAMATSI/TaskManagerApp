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

    @Column(name = "task_Name")
    private String taskName;

    @Column(name = "task_Description")
    private String taskDescription;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "assignee_ID")
    private Assignee assignee;

    @OneToMany(mappedBy = "task")
    private List<SubTask> subTaskList;

    @Column(name = "completion_Level")
    private Double taskCompletionPercentage;

    @Column(name = "deadline")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate taskDeadline;

}
