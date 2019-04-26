package com.michaelirick.wguscheduler;
import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface WGUDao<T> {
    @Insert
    void insert(T t);

    @Update
    void update(T t);

    @Delete
    void delete(T t);

    LiveData<List<T>> all();
}
