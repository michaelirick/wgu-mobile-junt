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

    @Override
    @Query("select * from terms where id = :i and :t = :t order by id")
    LiveData<List<Term>> allFor(int i, String t);

    @Override
    @Query("select * from terms where id = :i limit 1")
    LiveData<Term> find(int i);
}
