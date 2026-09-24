package com.devshowcase.api.controller;

import com.devshowcase.api.dto.request.FeedbackRequest;
import com.devshowcase.api.dto.response.FeedbackResponse;
import com.devshowcase.api.service.FeedbackService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/projects")
public class FeedbackController {

    private final FeedbackService feedbackService;

    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @PostMapping("/{id}/feedbacks")
    @ResponseStatus(HttpStatus.CREATED)
    public FeedbackResponse create(
            @PathVariable Long id,
            @Valid @RequestBody FeedbackRequest request
    ) {
        return feedbackService.create(id, request);
    }
}