package com.michaelirick.wguscheduler.daos;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.michaelirick.wguscheduler.WGUDao;
import com.michaelirick.wguscheduler.models.Term;

import java.util.List;

@Dao
public interface TermDao extends WGUDao<Term> {

    @Override
    @Query("select * from terms order by id")
    LiveData<List<Term>> all ();
}
