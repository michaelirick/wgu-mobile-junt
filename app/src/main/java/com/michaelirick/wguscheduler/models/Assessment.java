package com.michaelirick.wguscheduler.models;

import android.arch.persistence.room.Entity;

import com.michaelirick.wguscheduler.Model;

import java.sql.Date;

@Entity(tableName = "assessments")
public class Assessment extends Model {
    private String name;
    private String type;
    private int courseID;
    private Date date;

    public Assessment(String name, String type, int courseID, Date date) {
        this.name = name;
        this.type = type;
        this.courseID = courseID;
        this.date = date;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
