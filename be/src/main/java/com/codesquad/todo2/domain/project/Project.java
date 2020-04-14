package com.codesquad.todo2.domain.project;

import com.codesquad.todo2.domain.category.Category;
import org.springframework.data.annotation.Id;

import java.util.List;
import java.util.stream.Collectors;

public class Project {
    @Id
    private Long id;
    private String title;
    private List<Category> categories;

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

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", categories=" + categories +
                '}';
    }

    public Category getCategoryById(long categoryId) {
        return categories.stream()
                .filter(category -> category.getId() == categoryId)
                .findFirst()
                .orElse(null); // TODO: handle not found  with orElseThrow
    }

    public List<Category> getCategoriesExcludingSoftDeleted() {
        return categories.stream()
                .filter(category -> !category.isDeleted())
                .collect(Collectors.toList());
    }
}
