package com.devshowcase.api.dto.response;

public record FeedbackResponse(
        Long id,
        Integer rating,
        String comment,
        Long projectId
) {}