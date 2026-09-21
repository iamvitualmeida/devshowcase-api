package com.devshowcase.api.service;

import com.devshowcase.api.dto.request.ProjectRequest;
import com.devshowcase.api.dto.response.ProjectResponse;
import com.devshowcase.api.dto.response.TechnologyResponse;
import com.devshowcase.api.model.Profile;
import com.devshowcase.api.model.Project;
import com.devshowcase.api.model.Technology;
import com.devshowcase.api.repository.ProfileRepository;
import com.devshowcase.api.repository.ProjectRepository;
import com.devshowcase.api.repository.TechnologyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final ProfileRepository profileRepository;
    private final TechnologyRepository technologyRepository;

    public ProjectService(
            ProjectRepository projectRepository,
            ProfileRepository profileRepository,
            TechnologyRepository technologyRepository) {

        this.projectRepository = projectRepository;
        this.profileRepository = profileRepository;
        this.technologyRepository = technologyRepository;
    }

    public ProjectResponse create(ProjectRequest request) {

        Profile profile = profileRepository.findById(request.profileId())
                .orElseThrow(() -> new RuntimeException("Perfil não encontrado"));

        Project project = new Project(
                request.title(),
                request.description(),
                request.url(),
                profile
        );

        if (request.technologyIds() != null && !request.technologyIds().isEmpty()) {

            List<Technology> technologies = technologyRepository
                    .findAllById(request.technologyIds());

            project.setTechnologies(technologies);
        }

        Project savedProject = projectRepository.save(project);

        return toResponse(savedProject);
    }

    public List<ProjectResponse> findAll() {

        return projectRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    private ProjectResponse toResponse(Project project) {

        List<TechnologyResponse> technologies = project.getTechnologies()
                .stream()
                .map(technology -> new TechnologyResponse(
                        technology.getId(),
                        technology.getName()
                ))
                .toList();

        return new ProjectResponse(
                project.getId(),
                project.getTitle(),
                project.getDescription(),
                project.getUrl(),
                project.getProfile().getId(),
                technologies
        );
    }
}