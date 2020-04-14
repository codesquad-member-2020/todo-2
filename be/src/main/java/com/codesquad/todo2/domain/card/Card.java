package com.codesquad.todo2.domain.card;

import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

public class Card {
    @Id
    private Long id;
    private String title;
    private String content;
    private LocalDateTime timeCreated;
    private boolean isDeleted;
    private Long user; // many to one key

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(LocalDateTime timeCreated) {
        this.timeCreated = timeCreated;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", timeCreated=" + timeCreated +
                ", isDeleted=" + isDeleted +
                ", user=" + user +
                '}';
    }
}
