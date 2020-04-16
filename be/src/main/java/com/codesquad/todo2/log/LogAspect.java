package com.codesquad.todo2.log;

import com.codesquad.todo2.domain.card.CardDto;
import com.codesquad.todo2.domain.project.Project;
import com.codesquad.todo2.domain.project.ProjectService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {

    @Autowired
    private LogService logService;

    @Autowired
    private ProjectService projectService;

    @Around("execution(* com.codesquad.todo2.domain.project.ProjectService.addCard(..))")
    public Object logAddCard(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        Long projectId = (Long) args[0];
        Long categoryId = (Long) args[1];
        Long userId = (Long) args[3];

        CardDto cardDto = (CardDto) joinPoint.proceed();
        Long cardId = cardDto.getId();
        String cardTitle = cardDto.getTitle();

        String dstCategoryTitle = logService.findCategoryTitleById(categoryId);
        Project project = projectService.findProjectByIdOrHandleNotFound(projectId);

        Log log = new Log(userId, cardId, cardTitle, null, dstCategoryTitle, "added");
        project.addLog(log);
        projectService.saveProject(project);

        return cardDto;
    }
}
