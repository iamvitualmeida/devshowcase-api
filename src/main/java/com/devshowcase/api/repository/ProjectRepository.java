package com.devshowcase.api.repository;

import com.devshowcase.api.model.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    @Override
    @EntityGraph(attributePaths = {"technologies", "profile"})
    List<Project> findAll();

    @Override
    @EntityGraph(attributePaths = {"technologies", "profile"})
    Optional<Project> findById(Long id);

    @EntityGraph(attributePaths = {"technologies", "profile"})
    Page<Project> findAll(Pageable pageable);

    @EntityGraph(attributePaths = {"technologies", "profile"})
    Page<Project> findByTechnologies_Id(Long technologyId, Pageable pageable);
}