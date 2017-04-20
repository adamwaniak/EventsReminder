package com.example.adam.eventsreminder;

import java.io.Serializable;

/**
 * Created by Adam on 16.04.2017.
 */

public class EventDetailsRow implements Serializable{
    private String title;
    private String description;

    public EventDetailsRow(String title,String description){
        this.title = title;
        this.description = description;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
