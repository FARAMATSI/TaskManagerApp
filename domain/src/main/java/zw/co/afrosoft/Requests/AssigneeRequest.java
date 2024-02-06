package zw.co.afrosoft.Requests;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class AssigneeRequest {
    @JsonProperty("name")
    String name;
}
