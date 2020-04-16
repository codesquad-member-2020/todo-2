package com.codesquad.todo2.domain.project;

import com.codesquad.todo2.api.ResponseBodyWrapper;
import com.codesquad.todo2.domain.card.CardId;
import com.codesquad.todo2.domain.card.CardIds;
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
                                                       @RequestBody CardTitleContent requestBody,
                                                        @RequestAttribute("userName") String userName) {
        CardId cardId = projectService.addCard(projectId, categoryId, requestBody, userName);
        return ResponseEntity.ok(ResponseBodyWrapper.ok(cardId));
    }

    @PutMapping("/projects/{projectId}/categories/{categoryId}/cards/{cardId}")
    public ResponseEntity<ResponseBodyWrapper> editCard(@PathVariable long projectId,
                                                        @PathVariable long categoryId,
                                                        @PathVariable long cardId,
                                                        @RequestBody CardTitleContent requestBody) {
        boolean edited = projectService.editCard(projectId, categoryId, cardId, requestBody);
        if (edited) {
            return ResponseEntity.ok(ResponseBodyWrapper.ok());
        }
        return ResponseEntity.badRequest()
                .body(ResponseBodyWrapper.failed("카드 내용 변경 실패"));
    }

    @DeleteMapping("/projects/{projectId}/categories/{categoryId}/cards/{cardId}")
    public ResponseEntity<ResponseBodyWrapper> deleteCard(@PathVariable long projectId,
                                                              @PathVariable long categoryId,
                                                              @PathVariable long cardId) {
        boolean deleted = projectService.softDeleteCard(projectId, categoryId, cardId);
        if (deleted) {
            return ResponseEntity.ok(ResponseBodyWrapper.ok());
        }
        return ResponseEntity.badRequest()
                .body(ResponseBodyWrapper.failed("카드 삭제 실패. 존재하는 카드였을까?"));
    }

    @PutMapping("/projects/{projectId}/categories/{categoryId}/cards")
    public ResponseEntity<ResponseBodyWrapper> reorderCard(@PathVariable long projectId,
                                                           @PathVariable long categoryId,
                                                           @RequestBody CardIds requestBody) {
        boolean moved = projectService.reorderCard(projectId, categoryId, requestBody);
        if (moved) {
            return ResponseEntity.ok(ResponseBodyWrapper.ok());
        }
        return ResponseEntity.badRequest()
                .body(ResponseBodyWrapper.failed("카드 이동 실패."));
    }
}
