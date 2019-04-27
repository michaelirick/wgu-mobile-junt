package com.michaelirick.wguscheduler.repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;

import com.michaelirick.wguscheduler.Repository;
import com.michaelirick.wguscheduler.WGUDao;
import com.michaelirick.wguscheduler.WGUDatabase;
import com.michaelirick.wguscheduler.models.Term;

import java.util.List;

public class TermRepository extends Repository<Term> {
    public TermRepository(Application app) {
        WGUDatabase database = WGUDatabase.getInstance(app);
        dao = database.getDao(Term.class);
        cachedList = dao.all();
    }
}