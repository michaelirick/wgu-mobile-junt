package com.michaelirick.wguscheduler.views.assessments;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.michaelirick.wguscheduler.R;

public class AssessmentView extends RecyclerView.ViewHolder {
    public AssessmentView(View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.name);
        type = itemView.findViewById(R.id.type);
        date = itemView.findViewById(R.id.date);
    }

    public TextView name;
    public TextView type;
    public TextView date;
}
