package com.michaelirick.wguscheduler.daos;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.michaelirick.wguscheduler.WGUDao;
import com.michaelirick.wguscheduler.models.Note;

import java.util.List;

@Dao
public interface NoteDao extends WGUDao<Note> {
    @Override
    @Query("select * from notes where id = :i limit 1")
    LiveData<Note> find(int i);
    @Override
    @Query("select * from notes order by id")
    LiveData<List<Note>> all ();

    @Override
    @Query("select * from notes where id = :i order by id")
    LiveData<List<Note>> allFor(int i);
}
