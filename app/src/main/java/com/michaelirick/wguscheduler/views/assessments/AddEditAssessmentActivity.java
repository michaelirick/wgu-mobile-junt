package com.michaelirick.wguscheduler.views.assessments;

import android.content.Intent;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

import com.michaelirick.wguscheduler.AddEditActivity;
import com.michaelirick.wguscheduler.Converters;
import com.michaelirick.wguscheduler.Index;
import com.michaelirick.wguscheduler.R;
import com.michaelirick.wguscheduler.adapters.AlertAdapter;
import com.michaelirick.wguscheduler.models.Alert;
import com.michaelirick.wguscheduler.models.Assessment;
import com.michaelirick.wguscheduler.views.alerts.AddEditAlertActivity;
import com.michaelirick.wguscheduler.views.alerts.AlertViewModel;

import java.util.Date;

import static com.michaelirick.wguscheduler.Converters.setDatePickerValue;

public class AddEditAssessmentActivity extends AddEditActivity<Assessment> {
    public EditText name;
    String type;
    int filterId;
    DatePicker datePicker;
    Index<Alert> alertsIndex;

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.radio_objective:
                if(checked)
                    setType("Objective");
                break;
            case R.id.radio_performance:
                if(checked)
                    setType("Performance");
                break;
        }
    }

    public void setType(String t) {
        type = t;
        if(type == "Performance") {
            RadioButton button = findViewById(R.id.radio_performance);
            button.setChecked(true);
        }
        if(type == "Objective") {
            RadioButton button = findViewById(R.id.radio_objective);
            button.setChecked(true);
        }
    }

    @Override
    public void setupView() {
        setContentView(R.layout.activity_add_edit_assessment);
        name = findViewById(R.id.edit_text_title);
        datePicker = findViewById(R.id.datepicker);
        setupPanels();
        setupAlerts();
    }

    private void setupAlerts() {
        alertsIndex = new Index<Alert>(
                Alert.class,
                this,
                AlertViewModel.class,
                R.id.add_alert,
                R.id.alerts_recycler_view,
                AddEditAlertActivity.class,
                new AlertAdapter()
        );
        alertsIndex.filterId = thisID;
        alertsIndex.filterType = "Assessment";
        alertsIndex.add_request = Request.ADD_ALERTS;
        alertsIndex.edit_request = Request.EDIT_ALERTS;
        alertsIndex.create();

    }

    public void setupPanels() {
        setupPanel(R.id.toggle_info, R.id.info);
        setupPanel(R.id.toggle_alerts, R.id.alerts_list);
    }

    @Override
    public void setFields(Intent intent) {
        name.setText(intent.getStringExtra("name"));
        setDatePickerValue(datePicker, (Date) intent.getSerializableExtra("date"));
        setType(intent.getStringExtra("type"));
    }
    @Override
    public String getIdExtra(Intent intent) {
        return "id";
    }

    @Override
    public Intent dataFromFields() {
        Intent data = new Intent();
        data.putExtra("name", name.getText().toString());
        data.putExtra("courseID", filterID);
        data.putExtra("type", type);
        data.putExtra("id", thisID);
        data.putExtra("date", Converters.getDateFromDatePicker(datePicker));
        debug("#dataFromFields", data.toUri(0));
        return data;
    }

}
