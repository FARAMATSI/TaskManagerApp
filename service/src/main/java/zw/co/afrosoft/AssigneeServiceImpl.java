package zw.co.afrosoft;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import zw.co.afrosoft.Requests.AssigneeRequest;
import zw.co.afrosoft.model.Assignee;

@Service
@RequiredArgsConstructor
public class AssigneeServiceImpl implements AssigneeService {
    private final AssigneeRepository assigneeRepository;
    public Assignee createAssignee(AssigneeRequest assigneeRequest) {
        try {
            var assignee = Assignee.builder()
                    .name(assigneeRequest.getName())
                    .build();
            return assigneeRepository.save(assignee);
        } catch (Exception e) {
            e.printStackTrace();
            return new Assignee();
        }
    }



}