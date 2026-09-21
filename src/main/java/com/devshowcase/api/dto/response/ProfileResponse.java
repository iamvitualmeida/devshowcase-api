package com.devshowcase.api.dto.response;

public record ProfileResponse(
        Long id,
        String name,
        String email,
        String bio
) {
}