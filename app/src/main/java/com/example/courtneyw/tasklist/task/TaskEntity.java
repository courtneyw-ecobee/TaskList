package com.example.courtneyw.tasklist.task;

/**
 * Created by courtney.w on 11/8/17.
 */

public class TaskEntity {
    private final String title;
    private final String description;
    private final String date;
    private final String id;

    public TaskEntity(String title, String description, String date, String id) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }

    public String getId() {
        return id;
    }
}
