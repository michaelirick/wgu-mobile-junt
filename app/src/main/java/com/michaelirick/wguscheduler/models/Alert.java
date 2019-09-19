package com.michaelirick.wguscheduler.models;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.content.Context;
import android.content.Intent;

import com.michaelirick.wguscheduler.ApplicationActivity;
import com.michaelirick.wguscheduler.Model;

import java.sql.Time;
import java.util.Date;

@Entity(tableName = "alerts")
public class Alert extends Model {
    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setModelID(int modelID) {
        this.modelID = modelID;
    }

    public void setModelType(String modelType) {
        this.modelType = modelType;
    }

    private String title;
    private String description;
    private Date date;
    private int modelID;
    private String modelType;

    @Ignore
    public Alert(String title, String description, Date date, int modelID, String modelType) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.modelID = modelID;
        this.modelType = modelType;
    }


    public Alert(String title, String description, Date date) {
        this.title = title;
        this.description = description;
        this.date = date;
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

    public void create(ApplicationActivity app, Class receiverClass) {
        Intent intent = new Intent(app, receiverClass);
        intent.putExtra("alert_title", title);
        intent.putExtra("alert_description", description);
        PendingIntent sender = PendingIntent.getBroadcast(app, 0, intent, 0);
        AlarmManager alarmManager = (AlarmManager) app.getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, date.getTime(), sender);
    }

    public static AlarmManager createAlarm(ApplicationActivity app, Class receiverClass, Date date) {
        Intent intent = new Intent(app, receiverClass);
        PendingIntent sender = PendingIntent.getBroadcast(app, 0, intent, 0);
        AlarmManager alarmManager = (AlarmManager) app.getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, date.getTime(), sender);
        return alarmManager;
    }

    public Alert(Intent data, Class activity) {
        title = data.getStringExtra("title");
        description = data.getStringExtra("description");
        date = (Date) data.getSerializableExtra("date");
        modelID = data.getIntExtra("modelID", 0);
        modelType = data.getStringExtra("modelType");
    }

    public Intent toIntent(Context context, Class activity) {
        Intent intent = new Intent(context, activity);
        intent.putExtra("title", title);
        intent.putExtra("description", description);
        intent.putExtra("date", date);
        intent.putExtra("modelID", modelID);
        intent.putExtra("modelType", modelType);
        return intent;
    }
}
