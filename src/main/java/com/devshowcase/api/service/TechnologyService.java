package com.devshowcase.api.service;

import com.devshowcase.api.dto.request.TechnologyRequest;
import com.devshowcase.api.dto.response.TechnologyResponse;
import com.devshowcase.api.entity.Technology;
import com.devshowcase.api.repository.TechnologyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TechnologyService {

    private final TechnologyRepository technologyRepository;

    public TechnologyService(TechnologyRepository technologyRepository) {
        this.technologyRepository = technologyRepository;
    }

    public TechnologyResponse create(TechnologyRequest request) {

        Technology technology = new Technology(
                request.getName()
        );

        Technology savedTechnology = technologyRepository.save(technology);

        return new TechnologyResponse(
                savedTechnology.getId(),
                savedTechnology.getName()
        );
    }

    public List<TechnologyResponse> findAll() {

        return technologyRepository.findAll()
                .stream()
                .map(technology -> new TechnologyResponse(
                        technology.getId(),
                        technology.getName()
                ))
                .toList();
    }
}