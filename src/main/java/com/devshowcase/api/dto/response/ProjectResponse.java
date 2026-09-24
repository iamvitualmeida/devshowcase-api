package com.devshowcase.api.dto.response;

import java.util.List;

public record ProjectResponse(
        Long id,
        String title,
        String description,
        String url,
        Long profileId,
        List<TechnologyResponse> technologies,
        Double averageRating,
        Long upvotes
) {
}