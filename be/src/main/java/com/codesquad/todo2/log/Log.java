package com.codesquad.todo2.log;

import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

public class Log {
    @Id
    private Long id;
    private Long user; // many to one key
    private Long card; // many to one key
    private String cardTitle; // 로깅 당시 card 이름
    private String srcCategory;
    private String dstCategory;
    private String action;
    private LocalDateTime timeLogged;

    public Log() {}

    public Log(Long user, Long card, String cardTitle, String srcCategory, String dstCategory, String action) {
        this.user = user;
        this.card = card;
        this.cardTitle = cardTitle;
        this.srcCategory = srcCategory;
        this.dstCategory = dstCategory;
        this.action = action;
        timeLogged = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
    }

    public Long getCard() {
        return card;
    }

    public void setCard(Long card) {
        this.card = card;
    }

    public String getCardTitle() {
        return cardTitle;
    }

    public void setCardTitle(String cardTitle) {
        this.cardTitle = cardTitle;
    }

    public String getSrcCategory() {
        return srcCategory;
    }

    public void setSrcCategory(String srcCategory) {
        this.srcCategory = srcCategory;
    }

    public String getDstCategory() {
        return dstCategory;
    }

    public void setDstCategory(String dstCategory) {
        this.dstCategory = dstCategory;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public LocalDateTime getTimeLogged() {
        return timeLogged;
    }

    public void setTimeLogged(LocalDateTime timeLogged) {
        this.timeLogged = LocalDateTime.now();
    }
}
