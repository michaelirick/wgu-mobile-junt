package com.michaelirick.wguscheduler.repositories;

import android.app.Application;

import com.michaelirick.wguscheduler.Repository;
import com.michaelirick.wguscheduler.WGUDatabase;
import com.michaelirick.wguscheduler.models.Note;

public class NoteRepository extends Repository<Note> {
    public NoteRepository(Application app) {
        WGUDatabase database = WGUDatabase.getInstance(app);
        dao = database.getDao(Note.class);
        cachedList = dao.all();
    }
}
