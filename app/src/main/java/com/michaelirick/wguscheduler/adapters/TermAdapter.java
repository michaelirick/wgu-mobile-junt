package com.michaelirick.wguscheduler.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.michaelirick.wguscheduler.Adapter;
import com.michaelirick.wguscheduler.R;
import com.michaelirick.wguscheduler.models.Term;
import com.michaelirick.wguscheduler.views.terms.TermView;

import java.util.ArrayList;
import java.util.List;

public class TermAdapter extends Adapter<Term, TermView> {
    @Override
    public TermView createView(View itemView) {
        return new TermView(itemView);
    }

    @Override
    public int getItemId() {
        return R.layout.term_item;
    }

    @Override
    public void setFields(TermView holder, Term current) {
        holder.textViewTitle.setText(current.getTitle());
        holder.textViewStartDate.setText(current.getStartDate().toString());
        holder.textViewEndDate.setText(current.getEndDate().toString());
    }
}
