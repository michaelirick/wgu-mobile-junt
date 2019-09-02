package com.michaelirick.wguscheduler.models;

import android.arch.persistence.room.Entity;

import com.michaelirick.wguscheduler.Model;

@Entity(tableName = "notes")
public class Note extends Model {
    private String text;

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    private int courseID;

    public Note(String text, int courseID) {
        this.text = text;
        this.courseID = courseID;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
