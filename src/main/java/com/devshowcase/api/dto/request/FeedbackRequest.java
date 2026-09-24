package com.devshowcase.api.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record FeedbackRequest(

        @NotNull(message = "Nota é obrigatória")
        @Min(value = 1, message = "A nota deve ser entre 1 e 5")
        @Max(value = 5, message = "A nota deve ser entre 1 e 5")
        Integer rating,

        @NotBlank(message = "Comentário é obrigatório")
        String comment

) {
}