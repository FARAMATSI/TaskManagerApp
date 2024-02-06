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

    private String taskName;

    private String taskDescription;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "assignee_ID")
    private Assignee assignee;

    @OneToMany(mappedBy = "task")
    private List<SubTask> subTaskList;

    @Column(name = "completion_Level")
    private Double taskCompletionLevel;

    @Column(name = "deadline")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate taskDeadline;

}
