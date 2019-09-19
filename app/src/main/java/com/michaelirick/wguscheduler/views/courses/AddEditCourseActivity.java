package com.michaelirick.wguscheduler.views.courses;

import android.app.DatePickerDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.michaelirick.wguscheduler.AddEditActivity;
import com.michaelirick.wguscheduler.ApplicationReceiver;
import com.michaelirick.wguscheduler.CollapsePanel;
import com.michaelirick.wguscheduler.Converters;
import com.michaelirick.wguscheduler.Index;
import com.michaelirick.wguscheduler.ModelSpinner;
import com.michaelirick.wguscheduler.R;
import com.michaelirick.wguscheduler.ViewModel;
import com.michaelirick.wguscheduler.adapters.AlertAdapter;
import com.michaelirick.wguscheduler.adapters.NoteAdapter;
import com.michaelirick.wguscheduler.models.Alert;
import com.michaelirick.wguscheduler.models.Assessment;
import com.michaelirick.wguscheduler.models.Course;
import com.michaelirick.wguscheduler.models.Note;
import com.michaelirick.wguscheduler.models.Term;
import com.michaelirick.wguscheduler.views.alerts.AddEditAlertActivity;
import com.michaelirick.wguscheduler.views.alerts.AlertViewModel;
import com.michaelirick.wguscheduler.views.notes.AddEditNoteActivity;
import com.michaelirick.wguscheduler.views.notes.NoteViewModel;
import com.michaelirick.wguscheduler.views.terms.TermViewModel;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;
import static com.michaelirick.wguscheduler.Converters.fromTimestamp;
import static com.michaelirick.wguscheduler.Converters.now;
import static com.michaelirick.wguscheduler.Converters.setDatePickerValue;

public class AddEditCourseActivity extends AddEditActivity<Course> {
    public static final String EXTRA_PREFIX = "com.michaelirick.wguscheduler.views.courses.AddEditCourseActivity.";
    public static final String EXTRA_ID =
            EXTRA_PREFIX + ".EXTRA_ID";
    public static final String EXTRA_TITLE = EXTRA_PREFIX + "EXTRA_TITLE";
    public static final String EXTRA_START_DATE =
            "com.michaelirick.wguscheduler.views.courses.AddEditCourseActivity.EXTRA_START_DATE";
    public static final String EXTRA_END_DATE =
            "com.michaelirick.wguscheduler.views.courses.AddEditCourseActivity.EXTRA_END_DATE";
    public static final String EXTRA_STATUS =
            "com.michaelirick.wguscheduler.views.courses.AddEditCourseActivity.EXTRA_STATUS";
    public static final String EXTRA_MENTOR_NAME =
            "com.michaelirick.wguscheduler.views.courses.AddEditCourseActivity.EXTRA_MENTOR_NAME";
    public static final String EXTRA_MENTOR_PHONE =
            "com.michaelirick.wguscheduler.views.courses.AddEditCourseActivity.EXTRA_MENTOR_PHONE";
    public static final String EXTRA_MENTOR_EMAIL =
            "com.michaelirick.wguscheduler.views.courses.AddEditCourseActivity.EXTRA_MENTOR_EMAIL";
    public static final String EXTRA_TERM_ID =
            "com.michaelirick.wguscheduler.views.courses.AddEditCourseActivity.EXTRA_TERM_ID";
    private static final int ADD_NOTE_REQUEST = 1;
    private static final int EDIT_NOTE_REQUEST = 2;

    private EditText editTextTitle;
    private DatePicker datePickerStartDate;
    private DatePicker datePickerEndDate;
    ModelSpinner<Term> selectTerm;

    private Index<Assessment> assessmentsIndex;
    private Index<Note> notesIndex;

    private Button buttonStartDateAlert;
    private Index<Alert> alertsIndex;

    void setupPanels() {
        setupPanel(R.id.toggle_info, R.id.course_info);
        setupPanel(R.id.toggle_assessments, R.id.assessments_list);
        setupPanel(R.id.toggle_alerts, R.id.alerts_list);
        setupPanel(R.id.toggle_notes, R.id.notes_list);


    }

    @Override
    public void setupView() {
        setContentView(R.layout.activity_add_edit_course);
        editTextTitle = findViewById(R.id.edit_text_title);
        datePickerStartDate = findViewById(R.id.date_picker_start_date);
        datePickerEndDate = findViewById(R.id.date_picker_end_date);
        setupPanels();
        setupLists();
    }

    public void setupLists() {
        setupNotes();
        setupAlerts();
    }

    private void setupAlerts() {
        alertsIndex = new Index<Alert>(
                Alert.class,
                this,
                AlertViewModel.class,
                R.id.course_add_alert,
                R.id.course_alerts_recycler_view,
                AddEditAlertActivity.class,
                new AlertAdapter()
        );
        alertsIndex.filterId = thisID;
        alertsIndex.add_request = Request.ADD_ALERTS;
        alertsIndex.edit_request = Request.EDIT_ALERTS;
        alertsIndex.create();

    }

    public void setupNotes() {
        notesIndex = new Index<Note>(
                Note.class,
                this,
                NoteViewModel.class,
                R.id.course_add_note,
                R.id.course_notes_recycler_view,
                AddEditNoteActivity.class,
                new NoteAdapter()
        );
        notesIndex.filterId = thisID;
        notesIndex.add_request = Request.ADD_NOTE;
        notesIndex.edit_request = Request.EDIT_NOTE;
        notesIndex.create();
    }

    public void setupAssessments() {
//        assessmentsIndex = new Index<Term>(
//                Assessment.class,
//                this,
//                AssessmentViewModel.class,
//                R.id.button_add_assessment,
//                R.id.terms_recycler_view,
//                AddEditAssessmentActivity.class,
//                new AssessmentAdapter()
//        );
//        index.create();
    }

    @Override
    public void setFields(Intent intent) {
        boolean add = intent.getExtras() == null;
        if(!add)
        Log.d("test", intent.getExtras().toString());
        if(!add)
        editTextTitle.setText(intent.getStringExtra("title"));
        setDatePickerValue(datePickerStartDate, (Date) intent.getSerializableExtra("startDate"));
        setDatePickerValue(datePickerEndDate, (Date) intent.getSerializableExtra("endDate"));
        selectTerm = new ModelSpinner<Term>(
                this,
                (Spinner) findViewById(R.id.select_term_ids),
                new TermViewModel(getApplication()),
                intent.getIntExtra("termID", filterID)
        );
//        addAlertButton(
//                this,
//                R.id.start_date_alert,
//                intent.getStringExtra("title") + " Start Date",
//                "Your course " + intent.getStringExtra("title") + " is starting.",
//                (Date) intent.getSerializableExtra("startDate")
//        );
    }

    @Override
    public String getIdExtra(Intent intent) {
        return EXTRA_ID;
    }

    @Override
    public Intent dataFromFields() {
        Intent data = new Intent();
        data.putExtra("title", editTextTitle.getText().toString());

        data.putExtra("startDate", Converters.getDateFromDatePicker(datePickerStartDate));
        data.putExtra("endDate", Converters.getDateFromDatePicker(datePickerEndDate));
        data.putExtra("termID", selectTerm.getSelectedItem().id);

        return data;
    }

    @Override
    public void processResult(Request requestCode, int resultCode, Intent data) {
        Log.d("test", "AddEditCourseActivity#processResult: " + requestCode + ", " + resultCode);
        switch(requestCode) {
            case ADD_NOTE:
                Log.d("test", "Add Note");
                notesIndex.processResult(requestCode, resultCode, data);
                break;
            case EDIT_NOTE:
                Log.d("test", "Edit Note");
                notesIndex.processResult(requestCode, resultCode, data);
                break;
            case ADD_ALERTS:
                Alert a = alertsIndex.processResult(requestCode, resultCode, data);
                a.create(this, ApplicationReceiver.class);
                break;
            default:
                // do nothing
        }
    }


}
