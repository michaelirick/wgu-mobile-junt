package com.michaelirick.wguscheduler.models;

import android.arch.persistence.room.Entity;

import com.michaelirick.wguscheduler.Model;

@Entity(tableName = "notes")
public class Note extends Model {
    private String text;

    public Note(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
