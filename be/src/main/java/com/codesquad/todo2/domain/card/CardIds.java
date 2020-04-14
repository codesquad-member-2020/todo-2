package com.codesquad.todo2.domain.card;

public class CardIds {
    private Long cardId;
    private Long previousCardId;

    public Long getCardId() {
        return cardId;
    }

    public void setCardId(Long cardId) {
        this.cardId = cardId;
    }

    public Long getPreviousCardId() {
        return previousCardId;
    }

    public void setPreviousCardId(Long previousCardId) {
        this.previousCardId = previousCardId;
    }

    @Override
    public String toString() {
        return "CardIds{" +
                "cardId=" + cardId +
                ", previousCardId=" + previousCardId +
                '}';
    }
}
