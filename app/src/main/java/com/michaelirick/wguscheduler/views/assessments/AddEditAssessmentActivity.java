package com.michaelirick.wguscheduler.views.assessments;

import android.content.Intent;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.michaelirick.wguscheduler.AddEditActivity;
import com.michaelirick.wguscheduler.Converters;
import com.michaelirick.wguscheduler.R;
import com.michaelirick.wguscheduler.models.Assessment;

public class AddEditAssessmentActivity extends AddEditActivity<Assessment> {
    EditText name;
    Spinner type;
    int filterId;
    DatePicker datePicker;

    @Override
    public void setupView() {
        setContentView(R.layout.activity_add_edit_assessment);
        name = findViewById(R.id.edit_text_title);
        datePicker = findViewById(R.id.datepicker);
    }

    @Override
    public void setFields(Intent intent) {
        boolean add = intent.getExtras() == null;
        if (!add){

        }
    }
    @Override
    public String getIdExtra(Intent intent) {
        return "id";
    }

    @Override
    public Intent dataFromFields() {
        Intent data = new Intent();

        return data;
    }

}
