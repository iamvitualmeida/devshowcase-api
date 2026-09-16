package com.devshowcase.api.dto.request;

import jakarta.validation.constraints.NotBlank;

public class TechnologyRequest {

    @NotBlank(message = "Nome da tecnologia é obrigatório")
    private String name;

    public TechnologyRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}