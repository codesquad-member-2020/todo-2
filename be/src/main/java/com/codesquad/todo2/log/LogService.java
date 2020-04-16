package com.codesquad.todo2.log;

import com.codesquad.todo2.domain.project.Project;
import com.codesquad.todo2.domain.project.ProjectService;
import com.codesquad.todo2.domain.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LogService {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserService userService;

    public List<LogDto> mapProjectToLogDtos(long projectId) {
        Project project = projectService.findProjectByIdOrHandleNotFound(projectId);
        List<Log> logs = project.getLogs();
        List<LogDto> logDtos = new ArrayList<>();
        for (Log log : logs) {
            LogDto logDto = new LogDto();
            logDto.setUser(log.getUser());
            String userName = userService.findNameById(log.getUser());
            logDto.setUserName(userName);
            logDto.setCard(log.getCard());
            logDto.setCardTitle(log.getCardTitle());
            logDto.setSrcCategory(log.getSrcCategory());
            logDto.setDstCategory(log.getDstCategory());
            logDto.setAction(log.getAction());
            logDto.setTimeLogged(log.getTimeLogged());
            logDtos.add(logDto);
        }
        return logDtos;
    }
}
