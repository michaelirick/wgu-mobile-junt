package com.michaelirick.wguscheduler.daos;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.michaelirick.wguscheduler.WGUDao;
import com.michaelirick.wguscheduler.models.Course;

import java.util.List;

@Dao
public interface CourseDao extends WGUDao<Course> {

    @Override
    @Query("select * from courses where id = :i limit 1")
    LiveData<Course> find(int i);

    @Override
    @Query("select * from courses order by id")
    LiveData<List<Course>> all ();

    @Override
    @Query("select * from courses where termID = :i order by id")
    LiveData<List<Course>> allFor(int i);
}
