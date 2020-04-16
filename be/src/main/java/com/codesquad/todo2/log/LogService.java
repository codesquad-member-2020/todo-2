package com.codesquad.todo2.log;

import com.codesquad.todo2.domain.project.Project;
import com.codesquad.todo2.domain.project.ProjectRepository;
import com.codesquad.todo2.domain.project.ProjectService;
import com.codesquad.todo2.domain.user.UserService;
import com.codesquad.todo2.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LogService {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProjectRepository projectRepository;

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

    public String findCategoryTitleById(Long categoryId) {
        Optional<String> optionalCategoryTitle = projectRepository.findCategoryTitleById(categoryId);
        return optionalCategoryTitle.orElseThrow(() -> new NotFoundException("Category not found."));
    }
}
