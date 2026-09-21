package com.devshowcase.api.dto.request;

import jakarta.validation.constraints.NotBlank;

public record TechnologyRequest(

        @NotBlank(message = "Nome da tecnologia é obrigatório")
        String name

) {
}