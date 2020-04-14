package com.codesquad.todo2.domain.card;

public class CardId {
    private Long id;

    public CardId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "CardId{" +
                "id=" + id +
                '}';
    }
}
