package com.michaelirick.wguscheduler.models;

import android.arch.persistence.room.Entity;

import com.michaelirick.wguscheduler.Model;

import java.util.Date;

@Entity(tableName = "alerts")
public class Alert extends Model {
    private String title;
    private String description;
    private Date date;
    private int modelID;
    private String modelType;

    public Alert(String title, String description, Date date, int modelID, String modelType) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.modelID = modelID;
        this.modelType = modelType;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Date getDate() {
        return date;
    }

    public int getModelID() {
        return modelID;
    }

    public String getModelType() {
        return modelType;
    }
}
