package com.devshowcase.api.controller;

import com.devshowcase.api.dto.request.TechnologyRequest;
import com.devshowcase.api.dto.response.TechnologyResponse;
import com.devshowcase.api.service.TechnologyService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/technologies")
public class TechnologyController {

    private final TechnologyService technologyService;

    public TechnologyController(TechnologyService technologyService) {
        this.technologyService = technologyService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TechnologyResponse create(
            @Valid @RequestBody TechnologyRequest request) {

        return technologyService.create(request);
    }

    @GetMapping
    public List<TechnologyResponse> findAll() {
        return technologyService.findAll();
    }
}