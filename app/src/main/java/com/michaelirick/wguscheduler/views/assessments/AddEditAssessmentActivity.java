package com.michaelirick.wguscheduler.views.assessments;

import android.content.Intent;

import com.michaelirick.wguscheduler.AddEditActivity;
import com.michaelirick.wguscheduler.Converters;
import com.michaelirick.wguscheduler.R;
import com.michaelirick.wguscheduler.models.Assessment;

public class AddEditAssessmentActivity extends AddEditActivity<Assessment> {
    @Override
    public void setupView() {
        setContentView(R.layout.activity_add_edit_assessment);
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