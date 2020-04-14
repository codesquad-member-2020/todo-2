package com.codesquad.todo2.domain.project;

import com.codesquad.todo2.domain.card.Card;
import com.codesquad.todo2.domain.card.CardDto;
import com.codesquad.todo2.domain.category.Category;
import com.codesquad.todo2.domain.category.CategoryDto;
import com.codesquad.todo2.domain.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserService userService;

    public ProjectDto getProjectDtoByProjectId(Long projectId) {
        Optional<Project> optionalProject = projectRepository.findById(projectId);
        Project project = optionalProject.orElse(null); // TODO: handle 404 with orElseThrow
        return mapProjectToProjectDto(project);
    }

    private ProjectDto mapProjectToProjectDto(Project project) {
        ProjectDto projectDto = new ProjectDto();
        projectDto.setId(project.getId());
        projectDto.setTitle(project.getTitle());
        List<CategoryDto> categoryDtos = new ArrayList<>();
        for (Category category : project.getCategories()) {
            CategoryDto categoryDto = mapCategoryToCategoryDto(category);
            categoryDtos.add(categoryDto);
        }
        projectDto.setCategoryDtos(categoryDtos);
        return projectDto;
    }

    private CategoryDto mapCategoryToCategoryDto(Category category) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setTitle(category.getTitle());
        List<CardDto> cardDtos = new ArrayList<>();
        for (Card card : category.getCards()) {
            CardDto cardDto = mapCardToCardDto(card);
            cardDtos.add(cardDto);
        }
        categoryDto.setCardDtos(cardDtos);
        return categoryDto;
    }

    private CardDto mapCardToCardDto(Card card) {
        CardDto cardDto = new CardDto();
        cardDto.setId(card.getId());
        cardDto.setTitle(card.getTitle());
        cardDto.setContent(card.getContent());
        String userName = userService.findNameById(card.getUser());
        cardDto.setUserName(userName);
        return cardDto;
    }
}
