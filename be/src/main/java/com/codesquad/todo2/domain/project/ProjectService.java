package com.codesquad.todo2.domain.project;

import com.codesquad.todo2.domain.card.Card;
import com.codesquad.todo2.domain.card.CardDto;
import com.codesquad.todo2.domain.card.CardId;
import com.codesquad.todo2.domain.card.CardIds;
import com.codesquad.todo2.domain.card.CardTitleContent;
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

    public ProjectDto getProjectDtoByProjectId(long projectId) {
        Project project = findProjectByIdOrHandleNotFound(projectId);
        return mapProjectToProjectDto(project);
    }

    public CardId addCard(long projectId, long categoryId, CardTitleContent requestBody, String userName) {
        Project project = findProjectByIdOrHandleNotFound(projectId);
        Category category = project.getCategoryById(categoryId);
        Long userId = userService.findIdByName(userName);
        String title = requestBody.getTitle();
        String content = requestBody.getContent();

        Card card = new Card(title, content, userId);
        category.addCard(card);

        projectRepository.save(project);
        return new CardId(card.getId());
    }

    public boolean editCard(long projectId, long categoryId, long cardId, CardTitleContent requestBody) {
        Project project = findProjectByIdOrHandleNotFound(projectId);
        Category category = project.getCategoryById(categoryId);
        String title = requestBody.getTitle();
        String content = requestBody.getContent();

        Card card = category.getCardById(cardId);
        card.setTitle(title);
        card.setContent(content);

        projectRepository.save(project);
        return true; // TODO: handle failure case
    }

    public boolean softDeleteCard(long projectId, long categoryId, long cardId) {
        Project project = findProjectByIdOrHandleNotFound(projectId);
        Category category = project.getCategoryById(categoryId);
        Card card = category.getCardById(cardId);
        card.setDeleted(true);

        projectRepository.save(project);
        return true; // TODO: handle failure case
    }

    public boolean reorderCard(long projectId, long categoryId, CardIds requestBody) {
        Project project = findProjectByIdOrHandleNotFound(projectId);
        Category targetCategory = project.getCategoryById(categoryId);
        Long cardId = requestBody.getCardId();
        Long previousCardId = requestBody.getPreviousCardId();
        Category sourceCategory = findCategoryByCardIdFromProject(project, cardId);

        Card card = sourceCategory.removeCardById(cardId);
        targetCategory.addCardNextToCardById(card, previousCardId);

        projectRepository.save(project);
        return true; // TODO: handle failure case
    }

    public Project findProjectByIdOrHandleNotFound(Long projectId) {
        Optional<Project> optionalProject = projectRepository.findById(projectId);
        return optionalProject.orElse(null); // TODO: handle 404 with orElseThrow
    }

    private Category findCategoryByCardIdFromProject(Project project, long cardId) {
        Optional<Long> optionalCategoryId = projectRepository.findCategoryIdByCardId(cardId);
        Long categoryId = optionalCategoryId.orElse(null); // TODO: handle not found with orElseThrow
        return project.getCategoryById(categoryId);
    }

    private ProjectDto mapProjectToProjectDto(Project project) {
        ProjectDto projectDto = new ProjectDto();
        projectDto.setId(project.getId());
        projectDto.setTitle(project.getTitle());
        List<CategoryDto> categoryDtos = new ArrayList<>();
        for (Category category : project.getCategoriesExcludingSoftDeleted()) {
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
        for (Card card : category.getCardsExcludingSoftDeleted()) {
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
