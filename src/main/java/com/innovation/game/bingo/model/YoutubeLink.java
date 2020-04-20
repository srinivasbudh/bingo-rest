package com.innovation.game.bingo.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class YoutubeLink {

    private String link;

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

}
