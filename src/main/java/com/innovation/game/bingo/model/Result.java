package com.innovation.game.bingo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Result {
    @Id
    private String resultType;
    private Boolean isResultStatus;
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isResultStatus() {
        return isResultStatus;
    }

    public void setIsResultStatus(boolean loginSuccess) {
        isResultStatus = loginSuccess;
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }
}
