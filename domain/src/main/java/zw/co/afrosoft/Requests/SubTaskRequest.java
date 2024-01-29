package zw.co.afrosoft.Requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
@Data
public class SubTaskRequest {
    @JsonProperty("subTaskName")
    private String subTaskName;

    @JsonProperty("taskID")
    private Integer taskID;

}
