package com.michaelirick.wguscheduler.views.notes;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.michaelirick.wguscheduler.R;

public class NoteView extends RecyclerView.ViewHolder {
    public TextView textViewText;

    public NoteView(View itemView) {
        super(itemView);
        textViewText = itemView.findViewById(R.id.text_view_text);
    }
}

