package zw.co.afrosoft.Requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import zw.co.afrosoft.model.Assignee;
import zw.co.afrosoft.model.SubTask;

import java.time.LocalDate;
import java.util.List;

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
