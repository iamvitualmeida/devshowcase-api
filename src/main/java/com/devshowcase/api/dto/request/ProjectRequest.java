package com.devshowcase.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.util.List;

public record ProjectRequest(

        @NotBlank(message = "Título é obrigatório")
        String title,

        String description,

        @Pattern(
                regexp = "^$|https?://.+",
                message = "URL inválida"
        )
        String url,

        @NotNull(message = "Perfil é obrigatório")
        Long profileId,

        List<Long> technologyIds

) {
}