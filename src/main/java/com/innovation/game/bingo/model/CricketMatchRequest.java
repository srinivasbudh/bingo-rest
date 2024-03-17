package com.innovation.game.bingo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CricketMatchRequest {

    @JsonProperty(required = true)
    private String team1;
    @JsonProperty(required = true)
    private String team2;

    public String getTeam1() {
        return team1;
    }

    public void setTeam1(String team1) {
        this.team1 = team1;
    }

    public String getTeam2() {
        return team2;
    }

    public void setTeam2(String team2) {
        this.team2 = team2;
    }

}

