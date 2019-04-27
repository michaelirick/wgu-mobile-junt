package com.michaelirick.wguscheduler;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.michaelirick.wguscheduler.views.terms.TermView;

import java.util.ArrayList;
import java.util.List;

public abstract class Adapter<T, V extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<V> {
    public List<T> ts = new ArrayList<>();

    public abstract V createView(View itemView);
    public abstract int getItemId();
    public abstract void setFields(V holder, T current);

    @NonNull
    @Override
    public V onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(getItemId(), parent, false);
        return createView(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull V holder, int position) {
        T current = ts.get(position);
        setFields(holder, current);
    }

    @Override
    public int getItemCount() {
        return ts.size();
    }

    public void set(List<T> ts) {
        this.ts = ts;
        notifyDataSetChanged();
    }
}
