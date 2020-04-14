package com.codesquad.todo2.domain.project;

import com.codesquad.todo2.api.ResponseBodyWrapper;
import com.codesquad.todo2.domain.card.CardId;
import com.codesquad.todo2.domain.card.CardTitleContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @GetMapping("/projects/{projectId}")
    public ResponseEntity<ResponseBodyWrapper> viewProject(@PathVariable long projectId) {
        ProjectDto projectDto = projectService.getProjectDtoByProjectId(projectId);
        return ResponseEntity.ok(ResponseBodyWrapper.ok(projectDto));
    }

    @PostMapping("/projects/{projectId}/categories/{categoryId}/cards")
    public ResponseEntity<ResponseBodyWrapper> addCard(@PathVariable long projectId,
                                                       @PathVariable long categoryId,
                                                       @RequestBody CardTitleContent requestBody) {
                                                       // @RequestAttribute("userName") String userName) {
        String userName = "scott"; // TODO: replace with @RequestAttribute when interceptor is implemented
        CardId cardId = projectService.addCard(projectId, categoryId, requestBody, userName);
        return ResponseEntity.ok(ResponseBodyWrapper.ok(cardId));
    }
}
