package com.michaelirick.wguscheduler.views.terms;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import com.michaelirick.wguscheduler.AddEditActivity;
import com.michaelirick.wguscheduler.Converters;
import com.michaelirick.wguscheduler.Index;
import com.michaelirick.wguscheduler.R;
import com.michaelirick.wguscheduler.adapters.CourseAdapter;
import com.michaelirick.wguscheduler.models.Course;
import com.michaelirick.wguscheduler.models.Term;
import com.michaelirick.wguscheduler.views.courses.AddEditCourseActivity;
import com.michaelirick.wguscheduler.views.courses.CourseViewModel;

import java.util.Date;

import static com.michaelirick.wguscheduler.Converters.setDatePickerValue;

public class AddEditTermActivity extends AddEditActivity<Term> {
    public static final String EXTRA_ID =
            "com.michaelirick.wguscheduler.views.terms.EXTRA_ID";
    public static final String EXTRA_TITLE =
            "com.michaelirick.wguscheduler.views.terms.EXTRA_TITLE";
    public static final String EXTRA_START_DATE =
            "com.michaelirick.wguscheduler.views.terms.EXTRA_START_DATE";
    public static final String EXTRA_END_DATE =
            "com.michaelirick.wguscheduler.views.terms.EXTRA_END_DATE";
    private static final int ADD_COURSE_REQUEST = 1;
    private static final int EDIT_COURSE_REQUEST = 2;

    private EditText editTextTitle;
    private DatePicker datePickerStartDate;
    private DatePicker datePickerEndDate;
    private Index<Course> courseIndex;
    public void setupCourses() {
        courseIndex = new Index<Course>(
                Course.class,
                this,
                CourseViewModel.class,
                R.id.term_add_course,
                R.id.term_courses_recycler_view,
                AddEditCourseActivity.class,
                new CourseAdapter()
        );
        courseIndex.filterId = thisID;
        courseIndex.add_request = ADD_COURSE_REQUEST;
        courseIndex.edit_request = EDIT_COURSE_REQUEST;
        courseIndex.create();
    }

    @Override
    public void setupView() {
        setContentView(R.layout.activity_add_edit_term);
        editTextTitle = findViewById(R.id.edit_text_title);
        datePickerStartDate = findViewById(R.id.date_picker_start_date);
        datePickerEndDate = findViewById(R.id.date_picker_end_date);
        setupPanel(R.id.toggle_info, R.id.term_info);
        setupPanel(R.id.toggle_courses, R.id.courses_list);
        setupCourses();
    }

    @Override
    public void setFields(Intent intent) {
        editTextTitle.setText(intent.getStringExtra(EXTRA_TITLE));
        setDatePickerValue(datePickerStartDate, intent.getLongExtra(EXTRA_START_DATE, 0));
        setDatePickerValue(datePickerEndDate, intent.getLongExtra(EXTRA_END_DATE, 0));
    }

    @Override
    public String getIdExtra(Intent intent) {
        return EXTRA_ID;
    }

    @Override
    public Intent dataFromFields() {
        Intent data = new Intent();
        data.putExtra(EXTRA_TITLE, editTextTitle.getText().toString());
        data.putExtra(EXTRA_START_DATE, Converters.getDateFromDatePicker(datePickerStartDate).getTime());
        data.putExtra(EXTRA_END_DATE, Converters.getDateFromDatePicker(datePickerEndDate));
        return data;
    }
}
