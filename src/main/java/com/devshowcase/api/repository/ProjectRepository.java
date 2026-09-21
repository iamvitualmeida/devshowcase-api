package com.devshowcase.api.repository;

import com.devshowcase.api.model.Project;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    @Override
    @EntityGraph(attributePaths = {"technologies", "profile"})
    List<Project> findAll();
}