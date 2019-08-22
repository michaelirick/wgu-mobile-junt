package com.michaelirick.wguscheduler.views.alerts;

import android.os.Bundle;

import com.michaelirick.wguscheduler.ApplicationActivity;
import com.michaelirick.wguscheduler.Index;
import com.michaelirick.wguscheduler.R;
import com.michaelirick.wguscheduler.adapters.AlertAdapter;
import com.michaelirick.wguscheduler.models.Alert;
import com.michaelirick.wguscheduler.views.alerts.AddEditAlertActivity;
import com.michaelirick.wguscheduler.views.alerts.AlertViewModel;

public class AlertIndexActivity extends ApplicationActivity {
    Index<Alert> index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert_index);
        setMenu();
        index= new Index<Alert>();
        index.klass = Alert.class;
        index.activity = this;
        index.viewModelClass = AlertViewModel.class;
        index.adapter = new AlertAdapter(); // might need to make a final var in this class
        index.addButtonId = R.id.button_add_alert;
        index.recyclerViewId = R.id.alert_recycler_view;
        index.addEditClass = AddEditAlertActivity.class;
        index.create();
    }
}
