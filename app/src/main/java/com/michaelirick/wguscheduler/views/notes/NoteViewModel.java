package com.michaelirick.wguscheduler.views.notes;

import android.app.Application;

import com.michaelirick.wguscheduler.ViewModel;
import com.michaelirick.wguscheduler.models.Note;
import com.michaelirick.wguscheduler.repositories.NoteRepository;

public class NoteViewModel extends ViewModel<Note> {
    public NoteViewModel(Application application) {
        super(application);
        repository = new NoteRepository(application);
        cachedList = repository.all();
    }
}
