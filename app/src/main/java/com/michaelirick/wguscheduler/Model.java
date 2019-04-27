package com.michaelirick.wguscheduler;

import android.arch.persistence.room.PrimaryKey;

public class Model {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
