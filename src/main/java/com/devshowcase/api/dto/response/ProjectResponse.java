package com.devshowcase.api.dto.response;

import java.util.List;

public class ProjectResponse {

    private Long id;
    private String title;
    private String description;
    private String url;
    private Long profileId;
    private List<TechnologyResponse> technologies;

    public ProjectResponse() {
    }

    public ProjectResponse(
            Long id,
            String title,
            String description,
            String url,
            Long profileId,
            List<TechnologyResponse> technologies) {

        this.id = id;
        this.title = title;
        this.description = description;
        this.url = url;
        this.profileId = profileId;
        this.technologies = technologies;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public Long getProfileId() {
        return profileId;
    }

    public List<TechnologyResponse> getTechnologies() {
        return technologies;
    }
}