package com.codesquad.todo2.log;

import com.codesquad.todo2.domain.card.CardDto;
import com.codesquad.todo2.domain.card.CardIds;
import com.codesquad.todo2.domain.category.Category;
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

    @Around("execution(* com.codesquad.todo2.domain.project.ProjectService.softDeleteCard(..))")
    public Object logDeleteCard(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        Long projectId = (Long) args[0];
        Long categoryId = (Long) args[1];
        Long cardId = (Long) args[2];
        Long userId = (Long) args[3];

        boolean returnValue = (boolean) joinPoint.proceed();
        String cardTitle = logService.findCardTitleById(cardId);
        String srcCategory = logService.findCategoryTitleById(categoryId);
        Project project = projectService.findProjectByIdOrHandleNotFound(projectId);

        Log log = new Log(userId, cardId, cardTitle, srcCategory, null, "removed");
        project.addLog(log);
        projectService.saveProject(project);

        return returnValue;
    }

    @Around("execution(* com.codesquad.todo2.domain.project.ProjectService.editCard(..))")
    public Object logEditCard(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        Long projectId = (Long) args[0];
        Long cardId = (Long) args[2];
        Long userId = (Long) args[4];

        boolean returnValue = (boolean) joinPoint.proceed();
        String cardTitle = logService.findCardTitleById(cardId);
        Project project = projectService.findProjectByIdOrHandleNotFound(projectId);

        Log log = new Log(userId, cardId, cardTitle, null, null, "updated");
        project.addLog(log);
        projectService.saveProject(project);

        return returnValue;
    }

     @Around("execution(* com.codesquad.todo2.domain.project.ProjectService.reorderCard(..))")
    public Object logReorderCard(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        Long projectId = (Long) args[0];
        Long dstCategoryId = (Long) args[1];
        CardIds requestBody = (CardIds) args[2];
        Long userId = (Long) args[3];
        Long cardId = requestBody.getCardId();
        Project project = projectService.findProjectByIdOrHandleNotFound(projectId);
        Category srcCategory = projectService.findCategoryByCardIdFromProject(project, cardId); //이전 카테고리가 맞을까
        String sourceCategoryTitle = srcCategory.getTitle();
        boolean returnValue = (boolean) joinPoint.proceed();
        String cardTitle = logService.findCardTitleById(cardId);
        project = projectService.findProjectByIdOrHandleNotFound(projectId);
        String dstCategoryTitle = logService.findCategoryTitleById(dstCategoryId); //목적지 카테고리가 맞을까
        if (sourceCategoryTitle.equals(dstCategoryTitle)) {
            sourceCategoryTitle = null;
        }
        Log log = new Log(userId, cardId, cardTitle, sourceCategoryTitle, dstCategoryTitle, "moved");
        project.addLog(log);
        projectService.saveProject(project);
        return returnValue;
    }
}
