package com.michaelirick.wguscheduler.views.notes;

import android.content.Intent;
import android.widget.EditText;

import com.michaelirick.wguscheduler.AddEditActivity;
import com.michaelirick.wguscheduler.R;
import com.michaelirick.wguscheduler.models.Note;

public class AddEditNoteActivity extends AddEditActivity<Note> {

    private EditText noteText;

    @Override
    public void setFields(Intent intent) {
        if(intent.hasExtra("text")) {
            noteText.setText(intent.getStringExtra("text"));
        }
    }

    @Override
    public String getIdExtra(Intent intent) {
        return null;
    }

    @Override
    public void setupView() {
        setContentView(R.layout.activity_add_edit_note);
        noteText = findViewById(R.id.new_note_text);
    }

    @Override
    public Intent dataFromFields() {
        Intent data = new Intent();
        data.putExtra("text", noteText.getText().toString());
        data.putExtra("courseID", filterID);
        return data;
    }
}
