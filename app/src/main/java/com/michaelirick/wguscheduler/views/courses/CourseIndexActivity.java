package com.michaelirick.wguscheduler.views.courses;

import android.content.Intent;
import android.os.Bundle;

import com.michaelirick.wguscheduler.ApplicationActivity;
import com.michaelirick.wguscheduler.Index;
import com.michaelirick.wguscheduler.R;
import com.michaelirick.wguscheduler.adapters.CourseAdapter;
import com.michaelirick.wguscheduler.models.Course;

public class CourseIndexActivity extends ApplicationActivity {
    private static final int TERMS_REQUEST = 1;
    private static final int COURSES_REQUEST = 2;
    private static final int ADD_REQUEST = 3;
    private static final int EDIT_REQUEST = 4;
    Index<Course> courseIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_index);
        setMenu();
        courseIndex = new Index<Course>();
        courseIndex.klass = Course.class;
        courseIndex.activity = this;
        courseIndex.viewModelClass = CourseViewModel.class;
        courseIndex.adapter = new CourseAdapter(); // might need to make a final var in this class
        courseIndex.addButtonId = R.id.button_add_course;
        courseIndex.recyclerViewId = R.id.courses_recycler_view;
        courseIndex.addEditClass = AddEditCourseActivity.class;
        courseIndex.create();
    }

    @Override
    public void processResult(int requestCode, int resultCode, Intent data) {
        courseIndex.processResult(requestCode, resultCode, data);
    }

}
