package com.michaelirick.wguscheduler.adapters;




        import android.view.View;

        import com.michaelirick.wguscheduler.Adapter;
        import com.michaelirick.wguscheduler.R;
        import com.michaelirick.wguscheduler.models.Note;
        import com.michaelirick.wguscheduler.views.notes.NoteView;

public class NoteAdapter extends Adapter<Note, NoteView> {
    @Override
    public NoteView createView(View itemView) {
        return new NoteView(itemView);
    }

    @Override
    public int getItemId() {
        return R.layout.course_item;
    }

    @Override
    public void setFields(NoteView holder, Note current) {
    }
}