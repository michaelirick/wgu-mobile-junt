package com.michaelirick.wguscheduler.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.michaelirick.wguscheduler.R;
import com.michaelirick.wguscheduler.models.Term;
import com.michaelirick.wguscheduler.views.TermView;

import java.util.ArrayList;
import java.util.List;

public class TermAdapter extends RecyclerView.Adapter<TermView> {
    private List<Term> terms = new ArrayList<>();

    @NonNull
    @Override
    public TermView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.term_item, parent, false);
        return new TermView(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TermView holder, int position) {
        Term current = terms.get(position);
        holder.textViewTitle.setText(current.getTitle());
        holder.textViewStartDate.setText(current.getStartDate().toString());
        holder.textViewEndDate.setText(current.getEndDate().toString());
    }

    @Override
    public int getItemCount() {
        return terms.size();
    }

    public void set(List<Term> terms) {
        this.terms = terms;
        notifyDataSetChanged();
    }
}
