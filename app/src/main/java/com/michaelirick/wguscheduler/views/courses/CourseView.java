package com.michaelirick.wguscheduler.views.courses;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import com.michaelirick.wguscheduler.Index;
import com.michaelirick.wguscheduler.R;
import com.michaelirick.wguscheduler.adapters.AssessmentAdapter;
import com.michaelirick.wguscheduler.models.Assessment;
import com.michaelirick.wguscheduler.models.Note;
import com.michaelirick.wguscheduler.views.assessments.AssessmentViewModel;

public class CourseView extends RecyclerView.ViewHolder {
    public TextView textViewTitle;
    public TextView textViewStartDate;
    public TextView textViewEndDate;
    public TextView textViewStatus;
    public TextView textViewMentorName;
    public TextView textViewMentorPhone;
    public TextView textViewMentorEmail;

    public CourseView(View itemView) {
        super(itemView);
        textViewTitle = itemView.findViewById(R.id.text_view_title);
        textViewStartDate = itemView.findViewById(R.id.text_view_start_date);
        textViewEndDate = itemView.findViewById(R.id.text_view_end_date);
        textViewStatus = itemView.findViewById(R.id.text_view_status);
        textViewMentorName = itemView.findViewById(R.id.text_view_mentor_name);
        textViewMentorPhone = itemView.findViewById(R.id.text_view_mentor_phone);
        textViewMentorEmail = itemView.findViewById(R.id.text_view_mentor_email);
    }
}
