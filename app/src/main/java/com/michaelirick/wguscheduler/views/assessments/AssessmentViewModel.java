package com.michaelirick.wguscheduler.views.assessments;

import android.app.Application;

import com.michaelirick.wguscheduler.ViewModel;
import com.michaelirick.wguscheduler.models.Assessment;
import com.michaelirick.wguscheduler.models.Course;
import com.michaelirick.wguscheduler.repositories.AssessmentRepository;
import com.michaelirick.wguscheduler.views.courses.CourseView;

public class AssessmentViewModel extends ViewModel<Assessment> {
    public AssessmentViewModel(Application application) {
        super(application);
        repository = new AssessmentRepository(application);
        cachedList = repository.all();
    }
}
