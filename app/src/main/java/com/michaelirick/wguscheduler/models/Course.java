package com.michaelirick.wguscheduler.models;

import android.app.Activity;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.michaelirick.wguscheduler.Model;

import java.util.Date;

import static com.michaelirick.wguscheduler.Converters.dateToTimestamp;
import static com.michaelirick.wguscheduler.Converters.now;

@Entity(tableName = "courses")
@ForeignKey(entity = Term.class, parentColumns = "id", childColumns = "termID")
public class Course extends Model {
    private String title;
    private Date startDate;
    private Date endDate;
    private String status;

    private String mentorName;
    private String mentorPhone;
    private String mentorEmail;

    private int termID;

    public Course(String title, Date startDate, Date endDate, String status, String mentorName, String mentorPhone, String mentorEmail, int termID) {
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.mentorName = mentorName;
        this.mentorPhone = mentorPhone;
        this.mentorEmail = mentorEmail;
        this.termID = termID;
    }

    public Course(Intent data, Class activity) {
        termID = data.getIntExtra("termID", 0);
        title = data.getStringExtra("title");
        id = data.getIntExtra("id", 0);
        startDate = (Date) data.getSerializableExtra("startDate");
        endDate = (Date) data.getSerializableExtra("endDate");
        mentorName = data.getStringExtra("mentorName");
        mentorEmail= data.getStringExtra("mentorEmail");
        mentorPhone = data.getStringExtra("mentorPhone");
        status = data.getStringExtra("status");
//        Log.d("test", "Course(" + data.getExtras().toString() + "): " + toLongString());
    }

    @Override
    public String toLongString() {
        return "startDate: " + startDate.toString();
    }

    public Intent toIntent(Context context, Class activity) {
        Intent intent = new Intent(context, activity);
        intent.putExtra("id", id);
        intent.putExtra("title", title);
        intent.putExtra("termID", termID);
        intent.putExtra("startDate", startDate);
        intent.putExtra("endDate", endDate);
        intent.putExtra("mentorName", mentorName);
        intent.putExtra("mentorEmail", mentorEmail);
        intent.putExtra("mentorPhone", mentorPhone);
        intent.putExtra("status", status);
        return intent;
    }

    public String getStatus() {
        return status;
    }

    public String getMentorName() {
        return mentorName;
    }

    public String getMentorPhone() {
        return mentorPhone;
    }

    public String getMentorEmail() {
        return mentorEmail;
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

    public int getTermID() {
        return termID;
    }

    public String toString() {
        return getTitle();
    }
}
