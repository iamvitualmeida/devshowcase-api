package com.devshowcase.api.service;

import com.devshowcase.api.dto.request.FeedbackRequest;
import com.devshowcase.api.dto.response.FeedbackResponse;
import com.devshowcase.api.exception.ResourceNotFoundException;
import com.devshowcase.api.model.Feedback;
import com.devshowcase.api.model.Project;
import com.devshowcase.api.repository.FeedbackRepository;
import com.devshowcase.api.repository.ProjectRepository;
import org.springframework.stereotype.Service;

@Service
public class FeedbackService {

    private final FeedbackRepository feedbackRepository;
    private final ProjectRepository projectRepository;

    public FeedbackService(
            FeedbackRepository feedbackRepository,
            ProjectRepository projectRepository
    ) {
        this.feedbackRepository = feedbackRepository;
        this.projectRepository = projectRepository;
    }

    public FeedbackResponse create(Long projectId, FeedbackRequest request) {

        Project project = projectRepository.findById(projectId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Projeto não encontrado"));

        Feedback feedback = new Feedback(
                request.rating(),
                request.comment(),
                project
        );

        Feedback savedFeedback = feedbackRepository.save(feedback);

        Double averageRating =
                feedbackRepository.findAverageRatingByProjectId(projectId);

        project.setAverageRating(averageRating);
        projectRepository.save(project);

        return new FeedbackResponse(
                savedFeedback.getId(),
                savedFeedback.getRating(),
                savedFeedback.getComment(),
                project.getId()
        );
    }
}