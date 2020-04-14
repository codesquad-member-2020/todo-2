package com.codesquad.todo2.domain.category;

import com.codesquad.todo2.domain.card.Card;
import org.springframework.data.annotation.Id;

import java.util.List;

public class Category {
    @Id
    private Long id;
    private String title;
    private boolean isDeleted;
    private List<Card> cards;

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

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", isDeleted=" + isDeleted +
                ", cards=" + cards +
                '}';
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public Card getCardById(long cardId) {
        for (Card card : cards) {
            if (card.getId() == cardId) {
                return card;
            }
        }
        return null; // TODO: handle not found
    }
}
