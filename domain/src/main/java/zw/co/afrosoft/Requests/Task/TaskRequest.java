package zw.co.afrosoft.Requests.Task;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;


import java.time.LocalDate;


@Data
public class TaskRequest {
    @JsonProperty("taskName")
    String taskName;

    @JsonProperty("taskDescription")
    String taskDescription;

    @JsonProperty("deadlineDate")
    LocalDate taskDeadline;

    @JsonProperty("AssigneeID")
    private Integer assigneeID;

}
