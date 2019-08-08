package com.michaelirick.wguscheduler.views.courses;

import android.app.DatePickerDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.michaelirick.wguscheduler.AddEditActivity;
import com.michaelirick.wguscheduler.Converters;
import com.michaelirick.wguscheduler.ModelSpinner;
import com.michaelirick.wguscheduler.R;
import com.michaelirick.wguscheduler.models.Course;
import com.michaelirick.wguscheduler.models.Term;
import com.michaelirick.wguscheduler.views.terms.TermViewModel;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.michaelirick.wguscheduler.Converters.fromTimestamp;
import static com.michaelirick.wguscheduler.Converters.now;
import static com.michaelirick.wguscheduler.Converters.setDatePickerValue;

public class AddEditCourseActivity extends AddEditActivity<Course> {
    public static final String EXTRA_PREFIX = "com.michaelirick.wguscheduler.views.courses.AddEditCourseActivity.";
    public static final String EXTRA_ID =
            EXTRA_PREFIX + ".EXTRA_ID";
    public static final String EXTRA_TITLE = EXTRA_PREFIX + "EXTRA_TITLE";
    public static final String EXTRA_START_DATE =
            "com.michaelirick.wguscheduler.views.courses.AddEditCourseActivity.EXTRA_START_DATE";
    public static final String EXTRA_END_DATE =
            "com.michaelirick.wguscheduler.views.courses.AddEditCourseActivity.EXTRA_END_DATE";
    public static final String EXTRA_STATUS =
            "com.michaelirick.wguscheduler.views.courses.AddEditCourseActivity.EXTRA_STATUS";
    public static final String EXTRA_MENTOR_NAME =
            "com.michaelirick.wguscheduler.views.courses.AddEditCourseActivity.EXTRA_MENTOR_NAME";
    public static final String EXTRA_MENTOR_PHONE =
            "com.michaelirick.wguscheduler.views.courses.AddEditCourseActivity.EXTRA_MENTOR_PHONE";
    public static final String EXTRA_MENTOR_EMAIL =
            "com.michaelirick.wguscheduler.views.courses.AddEditCourseActivity.EXTRA_MENTOR_EMAIL";
    public static final String EXTRA_TERM_ID =
            "com.michaelirick.wguscheduler.views.courses.AddEditCourseActivity.EXTRA_TERM_ID";
    private static final int COURSES_REQUEST = 1;

    private EditText editTextTitle;
    private DatePicker datePickerStartDate;
    private DatePicker datePickerEndDate;
    ModelSpinner<Term> selectTerm;


    @Override
    public void setupView() {
        setContentView(R.layout.activity_add_edit_course);
        editTextTitle = findViewById(R.id.edit_text_title);
        datePickerStartDate = findViewById(R.id.date_picker_start_date);
        datePickerEndDate = findViewById(R.id.date_picker_end_date);
    }

    @Override
    public void setFields(Intent intent) {
        boolean add = intent.getExtras() == null;
        if(!add)
        Log.d("test", intent.getExtras().toString());
        if(!add)
        editTextTitle.setText(intent.getStringExtra("title"));
        setDatePickerValue(datePickerStartDate, (Date) intent.getSerializableExtra("startDate"));
        setDatePickerValue(datePickerEndDate, (Date) intent.getSerializableExtra("endDate"));
        selectTerm = new ModelSpinner<Term>(
                this,
                (Spinner) findViewById(R.id.select_term_ids),
                new TermViewModel(getApplication()),
                intent.getIntExtra("termID", 0)
        );
    }

    @Override
    public String getIdExtra(Intent intent) {
        return EXTRA_ID;
    }

    @Override
    public Intent dataFromFields() {
        Intent data = new Intent();
        data.putExtra("title", editTextTitle.getText().toString());

        data.putExtra("startDate", Converters.getDateFromDatePicker(datePickerStartDate));
        data.putExtra("endDate", Converters.getDateFromDatePicker(datePickerEndDate));
        data.putExtra("termID", selectTerm.getSelectedItem().id);

        return data;
    }

    public void coursesIndex(View view) {
        Intent intent = new Intent(AddEditCourseActivity.this, CoursesActivity.class);
        // intent.putExtra(AddEditCourseActivity.EXTRA_ID, t.getId());
        startActivityForResult(intent, COURSES_REQUEST);
    }
}
