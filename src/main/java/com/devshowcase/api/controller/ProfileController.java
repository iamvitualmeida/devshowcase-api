package com.devshowcase.api.controller;

import com.devshowcase.api.dto.request.ProfileRequest;
import com.devshowcase.api.dto.response.ProfileResponse;
import com.devshowcase.api.service.ProfileService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profiles")
public class ProfileController {

    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProfileResponse create(@Valid @RequestBody ProfileRequest request) {
        return profileService.create(request);
    }

    @GetMapping("/{id}")
    public ProfileResponse findById(@PathVariable Long id) {
        return profileService.findById(id);
    }
}