package com.michaelirick.wguscheduler.adapters;


import android.view.View;
import android.view.ViewGroup;

import com.michaelirick.wguscheduler.Adapter;
import com.michaelirick.wguscheduler.R;
import com.michaelirick.wguscheduler.models.Assessment;
import com.michaelirick.wguscheduler.models.Course;
import com.michaelirick.wguscheduler.views.assessments.AssessmentView;
import com.michaelirick.wguscheduler.views.courses.CourseView;

import java.util.ArrayList;
import java.util.List;

import static com.michaelirick.wguscheduler.Converters.setDateText;

public class AssessmentAdapter extends Adapter<Assessment, AssessmentView> {
    @Override
    public AssessmentView createView(View itemView) {
        return new AssessmentView(itemView);
    }

    @Override
    public int getItemId() {
        return R.layout.course_item;
    }

    @Override
    public void setFields(AssessmentView holder, Assessment current) {
//        holder.textViewTitle.setText(current.getTitle());
//        holder.textViewStatus.setText(current.getStatus());
//        holder.textViewMentorName.setText(current.getMentorName());
//        holder.textViewMentorEmail.setText(current.getMentorEmail());
//        holder.textViewMentorPhone.setText(current.getMentorPhone());
        //setDateText(holder.textViewStartDate, current.getStartDate());
        //setDateText(holder.textViewEndDate, current.getEndDate());
    }


}