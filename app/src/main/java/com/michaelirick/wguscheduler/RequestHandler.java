package com.michaelirick.wguscheduler;

import android.support.v7.app.AppCompatActivity;

import com.michaelirick.wguscheduler.views.alerts.AlertIndexActivity;

public class RequestHandler {
    public int id;
    public ApplicationActivity parent;
    public Class activityClass;
    public ApplicationActivity.Request request;

    public RequestHandler(ApplicationActivity parent, int id, Class activityClass, ApplicationActivity.Request request) {
        this.parent = parent;
        this.id = id;
        this.activityClass = activityClass;
        this.request = request;
    }

    public boolean check(int id) {
        if(id == this.id) {
            parent.startView(activityClass, request);
            return true;
        }
        return false;
    }
}
