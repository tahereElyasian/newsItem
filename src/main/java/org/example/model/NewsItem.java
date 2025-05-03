package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


public class NewsItem {
    private String headline;
    private int priority;

    public NewsItem(String headline, int priority) {
        this.headline = headline;
        this.priority = priority;
    }

    public int getPriority(){
        return priority;
    }

    public String getHeadline(){
        return headline;
    }
}

