package com.michaelirick.wguscheduler.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.content.Context;
import android.content.Intent;

import com.michaelirick.wguscheduler.Model;

import java.util.Date;

@Entity(tableName = "terms")
public class Term extends Model {
    private String title;
    private Date startDate;
    private Date endDate;

    public Term(String title, Date startDate, Date endDate) {
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Term(Intent data, Class activity) {
        id = data.getIntExtra("id", 0);
        title = data.getStringExtra("title");
        startDate = (Date) data.getSerializableExtra("startDate");
        endDate = (Date) data.getSerializableExtra("endDate");
    }

    public String getTitle() {
        return title;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public String toString() {
        return this.title;
    }

    public Intent toIntent(Context context, Class activity) {
        Intent intent = new Intent(context, activity);
        intent.putExtra("id", id);
        intent.putExtra("title", title);
        intent.putExtra("startDate", startDate);
        intent.putExtra("endDate", endDate);
        return intent;
    }
}
