package zw.co.afrosoft.Requests.Assignee;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class AssigneeRequest {
   // @JsonProperty("name")
   private String name;
   private String profession;
   private String department;
}
