package zw.co.afrosoft.Requests;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;
@Data
public class AssigneeRequest {
    @JsonProperty("name")
    String name;
}
