package com.michaelirick.wguscheduler.adapters;


import android.view.View;

import com.michaelirick.wguscheduler.Adapter;
import com.michaelirick.wguscheduler.R;
import com.michaelirick.wguscheduler.models.Alert;
import com.michaelirick.wguscheduler.views.alerts.AlertView;
import com.michaelirick.wguscheduler.views.assessments.AssessmentView;

import static com.michaelirick.wguscheduler.Converters.setDateText;

public class AlertAdapter extends Adapter<Alert, AlertView> {
    @Override
    public AlertView createView(View itemView) {
        return new AlertView(itemView);
    }

    @Override
    public int getItemId() {
        return R.layout.alert_item;
    }

    @Override
    public void setFields(AlertView holder, Alert current) {
        holder.textViewTitle.setText(current.getTitle());
        holder.textViewDescription.setText(current.getDescription());
        setDateText(holder.textViewDate, current.getDate());
    }


}