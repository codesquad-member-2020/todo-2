package com.codesquad.todo2.domain.project;

import com.codesquad.todo2.domain.category.CategoryDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.util.List;

@JsonTypeName("project")
public class ProjectDto {
    private Long id;
    private String title;
    @JsonProperty("categories")
    private List<CategoryDto> categoryDtos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<CategoryDto> getCategoryDtos() {
        return categoryDtos;
    }

    public void setCategoryDtos(List<CategoryDto> categoryDtos) {
        this.categoryDtos = categoryDtos;
    }

    @Override
    public String toString() {
        return "ProjectDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", categoryDtos=" + categoryDtos +
                '}';
    }
}
