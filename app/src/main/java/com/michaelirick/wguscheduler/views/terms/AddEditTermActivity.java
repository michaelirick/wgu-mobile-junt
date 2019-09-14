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
import com.michaelirick.wguscheduler.ApplicationActivity;
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
        courseIndex.add_request = Request.ADD_COURSE;
        courseIndex.edit_request = Request.EDIT_COURSE;
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
//        setupCourses();
    }

    @Override
    public void setFields(Intent intent) {
        editTextTitle.setText(intent.getStringExtra("title"));
        setDatePickerValue(datePickerStartDate, (Date) intent.getSerializableExtra("startDate"));
        setDatePickerValue(datePickerEndDate, (Date) intent.getSerializableExtra("endDate"));
    }

    @Override
    public String getIdExtra(Intent intent) {
        return "id";
    }

    @Override
    public Intent dataFromFields() {
        Intent data = new Intent();
        data.putExtra("title", editTextTitle.getText().toString());
        data.putExtra("startDate", Converters.getDateFromDatePicker(datePickerStartDate));
        data.putExtra("endDate", Converters.getDateFromDatePicker(datePickerEndDate));
        return data;
    }
}
