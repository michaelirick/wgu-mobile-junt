package com.michaelirick.wguscheduler.views.courses;

import android.app.Application;

import com.michaelirick.wguscheduler.ViewModel;
import com.michaelirick.wguscheduler.models.Course;
import com.michaelirick.wguscheduler.repositories.CourseRepository;

public class CourseViewModel extends ViewModel<Course> {
    public CourseViewModel(Application application) {
        super(application);
        repository = new CourseRepository(application);
        cachedList = repository.all();
    }
}
