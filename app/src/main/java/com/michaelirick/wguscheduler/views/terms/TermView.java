package com.michaelirick.wguscheduler.views.terms;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import com.michaelirick.wguscheduler.R;

public class TermView extends RecyclerView.ViewHolder {
    public TextView textViewTitle;
    public TextView textViewStartDate;
    public TextView textViewEndDate;

    public TermView(View itemView) {
        super(itemView);
        textViewTitle = itemView.findViewById(R.id.text_view_title);
        textViewStartDate = itemView.findViewById(R.id.text_view_start_date);
        textViewEndDate = itemView.findViewById(R.id.text_view_end_date);
    }
}