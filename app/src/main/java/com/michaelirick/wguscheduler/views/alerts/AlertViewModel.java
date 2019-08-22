package com.michaelirick.wguscheduler.views.alerts;

import android.app.Application;

import com.michaelirick.wguscheduler.ViewModel;
import com.michaelirick.wguscheduler.models.Alert;
import com.michaelirick.wguscheduler.models.Assessment;
import com.michaelirick.wguscheduler.repositories.AlertRepository;
import com.michaelirick.wguscheduler.repositories.AssessmentRepository;

public class AlertViewModel extends ViewModel<Alert> {
    public AlertViewModel(Application application) {
        super(application);
        repository = new AlertRepository(application);
        cachedList = repository.all();
    }
}
