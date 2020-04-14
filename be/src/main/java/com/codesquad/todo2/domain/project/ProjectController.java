package com.codesquad.todo2.domain.project;

import com.codesquad.todo2.api.ResponseBodyWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @GetMapping("/projects/{projectId}")
    public ResponseEntity<ResponseBodyWrapper> viewProject(@PathVariable Long projectId) {
        ProjectDto projectDto = projectService.getProjectDtoByProjectId(projectId);
        return ResponseEntity.ok(ResponseBodyWrapper.ok(projectDto));
    }
}
