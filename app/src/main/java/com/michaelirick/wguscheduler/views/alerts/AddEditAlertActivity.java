package com.michaelirick.wguscheduler.views.alerts;

import android.content.Intent;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import com.michaelirick.wguscheduler.AddEditActivity;
import com.michaelirick.wguscheduler.Converters;
import com.michaelirick.wguscheduler.R;
import com.michaelirick.wguscheduler.models.Assessment;

import java.util.Date;

import static com.michaelirick.wguscheduler.Converters.setDatePickerValue;
import static com.michaelirick.wguscheduler.Converters.setTimePickerValue;
import static com.michaelirick.wguscheduler.Converters.timeFromTimePicker;

public class AddEditAlertActivity extends AddEditActivity<Assessment> {
    int filterId;
    String filterType;
    DatePicker datePicker;
    TimePicker timePicker;
    EditText editTextTitle;
    EditText editTextDescription;


    @Override
    public void setupView() {
        setContentView(R.layout.activity_add_edit_alert);
        editTextTitle = findViewById(R.id.edit_text_title);
        editTextDescription = findViewById(R.id.edit_text_description);
        datePicker = findViewById(R.id.datepicker);
        timePicker = findViewById(R.id.timepicker);
    }

    @Override
    public void setFields(Intent intent) {

        Log.d("test", "AddEddAlertActivity#setFields: " + intent.toUri(0));
        editTextTitle.setText(intent.getStringExtra("title"));
        editTextDescription.setText(intent.getStringExtra("description"));
        setDatePickerValue(datePicker, (Date) intent.getSerializableExtra("date"));
        setTimePickerValue(timePicker, (Date) intent.getSerializableExtra("time"));
        filterId = intent.getIntExtra("filterID", 0);
        filterType = intent.getStringExtra("filterType");
    }

    @Override
    public String getIdExtra(Intent intent) {
        return "id";
    }

    @Override
    public Intent dataFromFields() {
        Intent data = new Intent();
        data.putExtra("title", editTextTitle.getText().toString());
        data.putExtra("description", editTextDescription.getText().toString());
        Date d = Converters.getDateFromDatePicker(datePicker);
        d.setTime(d.getTime() + timeFromTimePicker(timePicker));
        data.putExtra("date", d);
        data.putExtra("modelID", filterId);
        data.putExtra("modelType", filterType);
        Log.d("test", "AddEddAlertActivity#dataFromFields filterId: " + filterId);
        Log.d("test", "AddEddAlertActivity#dataFromFields: " + data.toUri(0));
        return data;
    }

}
