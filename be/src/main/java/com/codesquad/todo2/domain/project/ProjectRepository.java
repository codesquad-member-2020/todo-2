package com.codesquad.todo2.domain.project;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ProjectRepository extends CrudRepository<Project, Long> {

    @Query("SELECT category FROM card WHERE id = :cardId")
    Optional<Long> findCategoryIdByCardId(Long cardId);

    @Query("SELECT title FROM category WHERE id = :categoryId")
    Optional<String> findCategoryTitleById(Long categoryId);

    @Query("SELECT title FROM card WHERE id = :cardId")
    Optional<String> findCardTitleById(Long cardId);
}
