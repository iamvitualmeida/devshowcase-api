package com.devshowcase.api.controller;

import com.devshowcase.api.dto.request.ProjectRequest;
import com.devshowcase.api.dto.response.ProjectResponse;
import com.devshowcase.api.service.ProjectService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProjectResponse create(
            @Valid @RequestBody ProjectRequest request) {

        return projectService.create(request);
    }

    @GetMapping
    public Page<ProjectResponse> findAll(
            @RequestParam(required = false) Long technologyId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        return projectService.findAll(
                technologyId,
                PageRequest.of(page, size)
        );
    }

    @PutMapping("/{id}/upvote")
    public ProjectResponse upvote(@PathVariable Long id) {
        return projectService.upvote(id);
    }
}