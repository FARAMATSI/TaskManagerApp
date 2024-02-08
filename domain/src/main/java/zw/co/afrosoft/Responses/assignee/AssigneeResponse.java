package zw.co.afrosoft.Responses.assignee;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data

public class AssigneeResponse {
    private String status;
    private String message;
}
