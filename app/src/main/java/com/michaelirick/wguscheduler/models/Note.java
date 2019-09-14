package com.michaelirick.wguscheduler.models;

import android.arch.persistence.room.Entity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

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

    public Intent toIntent(Context context, Class activity) {
        Intent intent = new Intent(context, activity);
        intent.putExtra("id", id);
        intent.putExtra("text", text);
        intent.putExtra("courseID", courseID);
        return intent;
    }

    public Note(Intent data, Class activity) {
        id = data.getIntExtra("id", 0);
        text = data.getStringExtra("text");
        courseID = data.getIntExtra("courseID", 0);
        Log.d("test", "Note(" + data.getExtras().toString() + ")");
    }
}
