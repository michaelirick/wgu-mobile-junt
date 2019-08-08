package com.michaelirick.wguscheduler.views.courses;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.michaelirick.wguscheduler.Adapter;
import com.michaelirick.wguscheduler.ApplicationActivity;
import com.michaelirick.wguscheduler.Index;
import com.michaelirick.wguscheduler.IndexActivity;
import com.michaelirick.wguscheduler.R;
import com.michaelirick.wguscheduler.ViewModel;
import com.michaelirick.wguscheduler.adapters.CourseAdapter;
import com.michaelirick.wguscheduler.models.Course;

import java.util.Date;

import static com.michaelirick.wguscheduler.Converters.dateToTimestamp;

public class CoursesActivity extends AppCompatActivity {
    Index<Course> courseIndex;

    private static final int ADD_REQUEST = 1;
    private static final int EDIT_REQUEST = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("test", "onActivityResult: " + data.getExtras().toString());
        courseIndex.processResult(requestCode, resultCode, data);
    }
}
