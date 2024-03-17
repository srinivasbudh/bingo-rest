package com.innovation.game.bingo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PlayerRequest {

    @JsonProperty(required = true)
    private String playerName;
    @JsonProperty(required = true)
    private String playerType;
    @JsonProperty
    private float percentageOfPeopleSelected;



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

}

