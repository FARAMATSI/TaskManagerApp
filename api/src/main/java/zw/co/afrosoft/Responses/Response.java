package zw.co.afrosoft.Responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import zw.co.afrosoft.model.SubTask;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Response {
    private String status;
    private String message;
}

