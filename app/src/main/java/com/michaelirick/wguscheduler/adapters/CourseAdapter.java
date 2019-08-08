package com.michaelirick.wguscheduler.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.michaelirick.wguscheduler.Adapter;
import com.michaelirick.wguscheduler.R;
import com.michaelirick.wguscheduler.models.Course;
import com.michaelirick.wguscheduler.views.courses.CourseView;

import java.util.ArrayList;
import java.util.List;

import static com.michaelirick.wguscheduler.Converters.setDateText;

public class CourseAdapter extends Adapter<Course, CourseView> {
    @Override
    public CourseView createView(View itemView) {
        return new CourseView(itemView);
    }

    @Override
    public int getItemId() {
        return R.layout.course_item;
    }

    @Override
    public void setFields(CourseView holder, Course current) {
        holder.textViewTitle.setText(current.getTitle());
        holder.textViewStatus.setText(current.getStatus());
        holder.textViewMentorName.setText(current.getMentorName());
        holder.textViewMentorEmail.setText(current.getMentorEmail());
        holder.textViewMentorPhone.setText(current.getMentorPhone());
        //setDateText(holder.textViewStartDate, current.getStartDate());
        //setDateText(holder.textViewEndDate, current.getEndDate());
    }


}
