package com.innovation.game.bingo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;

public class Player {
    @Id
    private Long id;

    @JsonProperty(required = true)
    private String playerName;


    @JsonProperty(required = true)
    private String playerType;
    @JsonProperty(required = true)
    private float percentageOfPeopleSelected;

    @JsonProperty(required = true)
    private Team team;


    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerType() {
        return playerType;
    }

    public void setPlayerType(String playerType) {
        this.playerType = playerType;
    }

    public float getPercentageOfPeopleSelected() {
        return percentageOfPeopleSelected;
    }

    public void setPercentageOfPeopleSelected(float percentageOfPeopleSelected) {
        this.percentageOfPeopleSelected = percentageOfPeopleSelected;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }


    // Constructors, getters, and setters
}

