package com.michaelirick.wguscheduler.models;

import android.arch.persistence.room.Entity;

import com.michaelirick.wguscheduler.Model;

@Entity(tableName = "assessments")
public class Assessment extends Model {
    private String name;
    private String type;
    private int courseID;

    public Assessment(String name, String type, int courseID) {
        this.name = name;
        this.type = type;
        this.courseID = courseID;
    }


    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getCourseID() {
        return courseID;
    }
}
