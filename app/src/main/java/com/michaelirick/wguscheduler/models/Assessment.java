package com.michaelirick.wguscheduler.models;

import android.arch.persistence.room.Entity;
import android.content.Context;
import android.content.Intent;

import com.michaelirick.wguscheduler.Model;

import java.util.Date;

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

    public Assessment(Intent data, Class activity) {
        id = data.getIntExtra("id", 0);
        name = data.getStringExtra("name");
        type = data.getStringExtra("type");
        courseID = data.getIntExtra("courseID", 0);
        date = (Date) data.getSerializableExtra("date");

    }

    public Intent toIntent(Context context, Class activity) {
        Intent intent = new Intent(context, activity);
        intent.putExtra("id", id);
        intent.putExtra("name", name);
        intent.putExtra("type", type);
        intent.putExtra("date", date);
        intent.putExtra("courseID", courseID);
        return intent;

    }
}
