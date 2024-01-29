package zw.co.afrosoft;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import zw.co.afrosoft.Requests.AssigneeRequest;
import zw.co.afrosoft.model.Assignee;

@Service
@RequiredArgsConstructor
public class AssigneeServiceImpl implements AssigneeService {
    private final AssigneeRepository assigneeRepository;
    public Assignee createAssignee(AssigneeRequest assigneeRequest){
        var assignee = Assignee.builder()
                .name(assigneeRequest.getName())
                .build();
        return assigneeRepository.save(assignee);
    }
}