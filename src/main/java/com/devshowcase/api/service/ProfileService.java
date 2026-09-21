package com.devshowcase.api.service;

import com.devshowcase.api.dto.request.ProfileRequest;
import com.devshowcase.api.dto.response.ProfileResponse;
import com.devshowcase.api.model.Profile;
import com.devshowcase.api.repository.ProfileRepository;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {

    private final ProfileRepository profileRepository;

    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public ProfileResponse create(ProfileRequest request) {

        Profile profile = new Profile(
                request.name(),
                request.email(),
                request.bio()
        );

        Profile savedProfile = profileRepository.save(profile);

        return new ProfileResponse(
                savedProfile.getId(),
                savedProfile.getName(),
                savedProfile.getEmail(),
                savedProfile.getBio()
        );
    }

    public ProfileResponse findById(Long id) {

        Profile profile = profileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Perfil não encontrado"));

        return new ProfileResponse(
                profile.getId(),
                profile.getName(),
                profile.getEmail(),
                profile.getBio()
        );
    }
}