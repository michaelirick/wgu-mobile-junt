package com.michaelirick.wguscheduler;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;

import com.michaelirick.wguscheduler.daos.TermDao;
import com.michaelirick.wguscheduler.models.Term;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import com.michaelirick.wguscheduler.daos.TermDao;
import com.michaelirick.wguscheduler.models.Term;

import java.util.List;

@Database(entities = {Term.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class WGUDatabase extends RoomDatabase {
    private static com.michaelirick.wguscheduler.WGUDatabase instance;
    public abstract TermDao termDoa();

    public WGUDao getDao(Class dao) {
        if(dao.getName().equals("Term"))
            return termDoa();
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
