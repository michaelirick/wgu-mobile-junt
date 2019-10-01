package com.michaelirick.wguscheduler.daos;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.michaelirick.wguscheduler.WGUDao;
import com.michaelirick.wguscheduler.models.Alert;

import java.util.List;

@Dao
public interface AlertDao extends WGUDao<Alert> {
    @Override
    @Query("select * from alerts where id = :i limit 1")
    LiveData<Alert> find(int i);
    @Override
    @Query("select * from alerts order by id")
    LiveData<List<Alert>> all ();

    @Override
    @Query("select * from alerts where modelID = :i and modelType = :type order by id")
    LiveData<List<Alert>> allFor(int i, String type);

    @Query("select * from alerts where date(datetime(alerts.date / 1000 , 'unixepoch')) = date('now')")
    LiveData<List<Alert>> allForToday();
}
