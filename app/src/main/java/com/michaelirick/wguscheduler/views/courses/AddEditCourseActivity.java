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
import android.widget.RadioButton;
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
import com.michaelirick.wguscheduler.adapters.AssessmentAdapter;
import com.michaelirick.wguscheduler.adapters.NoteAdapter;
import com.michaelirick.wguscheduler.models.Alert;
import com.michaelirick.wguscheduler.models.Assessment;
import com.michaelirick.wguscheduler.models.Course;
import com.michaelirick.wguscheduler.models.Note;
import com.michaelirick.wguscheduler.models.Term;
import com.michaelirick.wguscheduler.views.alerts.AddEditAlertActivity;
import com.michaelirick.wguscheduler.views.alerts.AlertViewModel;
import com.michaelirick.wguscheduler.views.assessments.AddEditAssessmentActivity;
import com.michaelirick.wguscheduler.views.assessments.AssessmentViewModel;
import com.michaelirick.wguscheduler.views.notes.AddEditNoteActivity;
import com.michaelirick.wguscheduler.views.notes.NoteViewModel;
import com.michaelirick.wguscheduler.views.terms.TermViewModel;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;
import static com.michaelirick.wguscheduler.Converters.fromTimestamp;
import static com.michaelirick.wguscheduler.Converters.now;
import static com.michaelirick.wguscheduler.Converters.setDatePickerValue;

public class AddEditCourseActivity extends AddEditActivity<Course> {
    public static final String EXTRA_PREFIX = "com.michaelirick.wguscheduler.views.courses.AddEditCourseActivity.";
    public static final String EXTRA_ID =
            EXTRA_PREFIX + ".EXTRA_ID";


    private EditText editTextTitle;
    private EditText editTextMentorName;
    private EditText editTextMentorEmail;
    private EditText editTextMentorPhone;
    private DatePicker datePickerStartDate;
    private DatePicker datePickerEndDate;
    ModelSpinner<Term> selectTerm;

    private Index<Assessment> assessmentsIndex;
    private Index<Note> notesIndex;

    private Button buttonStartDateAlert;
    private Index<Alert> alertsIndex;

    private String status;

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
        editTextMentorName = findViewById(R.id.edit_text_mentor_name);
        editTextMentorEmail = findViewById(R.id.edit_text_mentor_email);
        editTextMentorPhone = findViewById(R.id.edit_text_mentor_phone);
        setupPanels();
        setupLists();
        setupAlerts();
    }

    public void setupLists() {
        setupNotes();
        setupAlerts();
        setupAssessments();
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
        alertsIndex.filterType = "Course";
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
        assessmentsIndex= new Index<Assessment>(
                Assessment.class,
                this,
                AssessmentViewModel.class,
                R.id.course_add_assessment,
                R.id.course_assessments_recycler_view,
                AddEditAssessmentActivity.class,
                new AssessmentAdapter()
        );
        assessmentsIndex.filterId = thisID;
        assessmentsIndex.add_request = Request.ADD_ASSESSMENT;
        assessmentsIndex.edit_request = Request.EDIT_ASSESSMENT;
        assessmentsIndex.create();
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
        editTextMentorName.setText(intent.getStringExtra("mentorName"));
        editTextMentorEmail.setText(intent.getStringExtra("mentorEmail"));
        editTextMentorPhone.setText(intent.getStringExtra("mentorPhone"));
        setStatus(intent.getStringExtra("status"));
    }

    @Override
    public String getIdExtra(Intent intent) {
        return EXTRA_ID;
    }

    @Override
    public Intent dataFromFields() {
        Intent data = new Intent();
        data.putExtra("title", editTextTitle.getText().toString());
        data.putExtra("mentorName", editTextMentorName.getText().toString());
        data.putExtra("mentorEmail", editTextMentorEmail.getText().toString());
        data.putExtra("mentorPhone", editTextMentorPhone.getText().toString());
        data.putExtra("status", status);
        data.putExtra("startDate", Converters.getDateFromDatePicker(datePickerStartDate));
        data.putExtra("endDate", Converters.getDateFromDatePicker(datePickerEndDate));
        data.putExtra("termID", selectTerm.getSelectedItem().id);

        return data;
    }

    @Override
    public void processResult(Request requestCode, int resultCode, Intent data) {
//        Log.d("test", "AddEditCourseActivity#processResult: " + requestCode + ", " + resultCode + ": " + data.toUri(0));
        Alert a;
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
                a = alertsIndex.processResult(requestCode, resultCode, data);
                if(a != null)
                    a.create(this, ApplicationReceiver.class);
                break;
            case EDIT_ALERTS:
                a = alertsIndex.processResult(requestCode, resultCode, data);
                if(a != null)
                    a.create(this, ApplicationReceiver.class);
                break;
            case ADD_ASSESSMENT:
            case EDIT_ASSESSMENT:
                assessmentsIndex.processResult(requestCode, resultCode, data);
                break;

            default:
                // do nothing
        }
    }

    public void setStatus(String status) {
        HashMap<String, Integer> radioButtons = new HashMap() {{
            put("In Progress", R.id.radio_in_progress);
            put("Completed", R.id.radio_completed);
            put("Plan To Take", R.id.radio_plan_to_take);
            put("Dropped", R.id.radio_dropped);
        }};
        for(Map.Entry<String, Integer> entry : radioButtons.entrySet()) {
            if(status == entry.getKey()) {
                RadioButton button = findViewById(entry.getValue());
                button.setChecked(true);
            }
        }
        this.status = status;
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.radio_in_progress:
                if(checked)
                    setStatus("In Progress");
                break;
            case R.id.radio_completed:
                if(checked)
                    setStatus("Completed");
                break;
            case R.id.radio_plan_to_take:
                if(checked)
                    setStatus("Plan To Take");
                break;
            case R.id.radio_dropped:
                if(checked)
                    setStatus("Dropped");
                break;
        }
    }
}
