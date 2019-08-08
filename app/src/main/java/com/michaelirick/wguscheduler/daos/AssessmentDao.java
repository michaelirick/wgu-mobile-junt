package com.michaelirick.wguscheduler.daos;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.michaelirick.wguscheduler.WGUDao;
import com.michaelirick.wguscheduler.models.Assessment;

import java.util.List;

@Dao
public interface AssessmentDao extends WGUDao<Assessment> {
    @Override
    @Query("select * from assessments where id = :i limit 1")
    LiveData<Assessment> find(int i);

    @Override
    @Query("select * from assessments order by id")
    LiveData<List<Assessment>> all ();

    @Override
    @Query("select * from assessments where courseID = :i order by id")
    LiveData<List<Assessment>> allFor (int i);
}
