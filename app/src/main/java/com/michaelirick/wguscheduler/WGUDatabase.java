package com.michaelirick.wguscheduler;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;

import com.michaelirick.wguscheduler.daos.AlertDao;
import com.michaelirick.wguscheduler.daos.AssessmentDao;
import com.michaelirick.wguscheduler.daos.CourseDao;
import com.michaelirick.wguscheduler.daos.TermDao;
import com.michaelirick.wguscheduler.models.Alert;
import com.michaelirick.wguscheduler.models.Assessment;
import com.michaelirick.wguscheduler.models.Course;
import com.michaelirick.wguscheduler.models.Term;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;
import android.os.Debug;
import android.util.Log;

import com.michaelirick.wguscheduler.daos.TermDao;
import com.michaelirick.wguscheduler.models.Term;

import java.util.List;

@Database(entities = {Term.class, Course.class, Assessment.class, Alert.class}, version = 2)
@TypeConverters({Converters.class})
public abstract class WGUDatabase extends RoomDatabase {
    private static com.michaelirick.wguscheduler.WGUDatabase instance;
    public abstract TermDao termDoa();
    public abstract CourseDao courseDoa();
    public abstract AssessmentDao assessmentDao();
    public abstract AlertDao alertDao();

    public WGUDao getDao(Class dao) {
        if(dao.getName().equals("com.michaelirick.wguscheduler.models.Term"))
            return termDoa();
        if(dao.getName().equals("com.michaelirick.wguscheduler.models.Course"))
            return courseDoa();
        if(dao.getName().equals("com.michaelirick.wguscheduler.models.Alert"))
            return alertDao();
        if(dao.getName().equals("com.michaelirick.wguscheduler.models.Assessment"))
            return assessmentDao();
        return null;
    }

    public static synchronized WGUDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    com.michaelirick.wguscheduler.WGUDatabase.class, "wgu_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
