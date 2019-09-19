package com.michaelirick.wguscheduler.views.alerts;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.michaelirick.wguscheduler.R;

public class AlertView extends RecyclerView.ViewHolder {
    public TextView textViewTitle;
    public TextView textViewDescription;
    public TextView textViewDate;
    public AlertView(View itemView) {
        super(itemView);
        textViewTitle = itemView.findViewById(R.id.text_view_title);
        textViewDescription = itemView.findViewById(R.id.text_view_description);
        textViewDate = itemView.findViewById(R.id.text_view_date);
    }
}
