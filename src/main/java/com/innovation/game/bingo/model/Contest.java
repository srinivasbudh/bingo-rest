package com.innovation.game.bingo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Contest {

    @Indexed(unique = true)
    private String contestId;
    private String name;

    public String getContestId() {
        return contestId;
    }

    public void setContestId(String id) {
        this.contestId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
