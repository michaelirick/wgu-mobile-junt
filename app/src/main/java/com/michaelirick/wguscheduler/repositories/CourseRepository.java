package com.michaelirick.wguscheduler.repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;

import com.michaelirick.wguscheduler.Repository;
import com.michaelirick.wguscheduler.WGUDao;
import com.michaelirick.wguscheduler.WGUDatabase;
import com.michaelirick.wguscheduler.models.Course;

import java.util.List;

public class CourseRepository extends Repository<Course> {
    public CourseRepository(Application app) {
        WGUDatabase database = WGUDatabase.getInstance(app);
        dao = database.getDao(Course.class);
        cachedList = dao.all();
    }
}