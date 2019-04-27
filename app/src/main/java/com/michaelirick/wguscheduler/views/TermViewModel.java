package com.michaelirick.wguscheduler.views;

import android.app.Application;

import com.michaelirick.wguscheduler.ViewModel;
import com.michaelirick.wguscheduler.models.Term;
import com.michaelirick.wguscheduler.repositories.TermRepository;

public class TermViewModel extends ViewModel<Term> {
    public TermViewModel(Application application) {
        super(application);
        repository = new TermRepository(application);
        cachedList = repository.all();
    }
}
