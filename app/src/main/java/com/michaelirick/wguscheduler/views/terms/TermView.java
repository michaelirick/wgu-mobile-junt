package com.michaelirick.wguscheduler.views.terms;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import com.michaelirick.wguscheduler.Index;
import com.michaelirick.wguscheduler.R;
import com.michaelirick.wguscheduler.models.Course;

public class TermView extends RecyclerView.ViewHolder {
    private static final int ADD_REQUEST = 1;
    private static final int EDIT_REQUEST = 2;
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