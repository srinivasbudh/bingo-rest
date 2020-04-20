package com.innovation.game.bingo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class BingoResult {

    @Id
    private Integer takenBall;

    public Integer getTakenBall() {
        return takenBall;
    }

    public void setTakenBall(Integer takenBall) {
        this.takenBall = takenBall;
    }

}
