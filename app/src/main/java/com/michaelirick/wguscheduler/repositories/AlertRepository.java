package com.michaelirick.wguscheduler.repositories;

import android.app.Application;

import com.michaelirick.wguscheduler.Repository;
import com.michaelirick.wguscheduler.WGUDatabase;
import com.michaelirick.wguscheduler.models.Alert;

import java.util.List;

public class AlertRepository extends Repository<Alert> {
    public AlertRepository(Application app) {
        WGUDatabase database = WGUDatabase.getInstance(app);
        dao = database.getDao(Alert.class);
        cachedList = dao.all();
    }
}
