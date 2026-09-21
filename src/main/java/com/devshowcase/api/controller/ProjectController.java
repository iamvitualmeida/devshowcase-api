package com.devshowcase.api.controller;

import com.devshowcase.api.dto.request.ProjectRequest;
import com.devshowcase.api.dto.response.ProjectResponse;
import com.devshowcase.api.service.ProjectService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public List<ProjectResponse> findAll() {
        return projectService.findAll();
    }
}