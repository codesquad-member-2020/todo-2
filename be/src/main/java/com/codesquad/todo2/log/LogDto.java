package com.codesquad.todo2.log;

import com.fasterxml.jackson.annotation.JsonTypeName;

import java.time.LocalDateTime;

@JsonTypeName("Log")
public class LogDto {
    private Long user; // many to one key
    private String userName; // many to one key
    private Long card; // many to one key
    private String cardTitle; // 로깅 당시 card 이름
    private String srcCategory;
    private String dstCategory;
    private String action;
    private LocalDateTime timeLogged;

    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
    }

    public String getUserName() {
        return userName;
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
        this.timeLogged = timeLogged;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setCardTitle(String cardTitle) {
        this.cardTitle = cardTitle;
    }
}
