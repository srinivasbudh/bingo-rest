package com.innovation.game.bingo.model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document
public class Bingo {
    private String username;
    private List<Integer> bingoNumber;

    private String contestId;
    private Date expiration;
    private String status;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Integer> getBingoNumber() {
        return bingoNumber;
    }

    public void setBingoNumber(List<Integer> bingoNumber) {
        this.bingoNumber = bingoNumber;
    }

    public String getContestId() {
        return contestId;
    }

    public void setContestId(String contestId) {
        this.contestId = contestId;
    }

    public Date getExpiration() {
        return expiration;
    }

    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
