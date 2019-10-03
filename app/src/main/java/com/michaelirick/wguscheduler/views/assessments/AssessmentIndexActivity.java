package com.michaelirick.wguscheduler.views.assessments;

import android.os.Bundle;

import com.michaelirick.wguscheduler.ApplicationActivity;
import com.michaelirick.wguscheduler.Index;
import com.michaelirick.wguscheduler.R;
import com.michaelirick.wguscheduler.adapters.AssessmentAdapter;
import com.michaelirick.wguscheduler.models.Assessment;

public class AssessmentIndexActivity extends ApplicationActivity {
    Index<Assessment> index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment_index);
        setMenu();
        index= new Index<Assessment>();
        index.klass = Assessment.class;
        index.activity = this;
        index.viewModelClass = AssessmentViewModel.class;
        index.adapter = new AssessmentAdapter(); // might need to make a final var in this class
        index.addButtonId = R.id.button_add_assessment;
        index.recyclerViewId = R.id.assessment_recycler_view;
        index.addEditClass = AddEditAssessmentActivity.class;
        index.add_request = Request.ADD_ASSESSMENT;
        index.edit_request = Request.EDIT_ASSESSMENT;
        index.create();
    }
}
