package zw.co.afrosoft.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;


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

    @Column(name = "taskName")
    private String taskName;

    @Column(name = "taskDescription")
    private String taskDescription;

    @JsonBackReference
    @ManyToOne
  //  @JoinColumn(name = "assigneeID")
    private Assignee assignee;

    @OneToMany(mappedBy = "task")
    private List<SubTask> subTaskList;

    @Column(name = "completionLevel")
    private Double taskCompletionLevel;

    @Column(name = "deadline")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate taskDeadline;

}
