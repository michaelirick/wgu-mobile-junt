package com.michaelirick.wguscheduler;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.michaelirick.wguscheduler.views.terms.TermView;

import java.util.ArrayList;
import java.util.List;

public abstract class Adapter<T, V extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<V> {
    public List<T> ts = new ArrayList<>();
    private OnItemClickListener listener;

    public abstract V createView(View itemView);
    public abstract int getItemId();
    public abstract void setFields(V holder, T current);

    public void setOnItemClickListener (AdapterView.OnItemClickListener listener) {
        setOnItemClickListener(listener);
    }

    @NonNull
    @Override
    public V onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(getItemId(), parent, false);
        final V v = createView(itemView);
        itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                int position = v.getAdapterPosition();
                if (listener != null && position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(ts.get(position));
                }
            }
        });

        return v;
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

    public interface OnItemClickListener<T> {
        void onItemClick(T t);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

//    public abstract RecyclerView.OnItemTouchListener newClickListener();
//    public abstract RecyclerView.Adapter<V>.
}
