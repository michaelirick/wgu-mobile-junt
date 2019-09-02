package com.michaelirick.wguscheduler;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Debug;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import com.michaelirick.wguscheduler.views.courses.AddEditCourseActivity;

import java.util.Calendar;
import java.util.Date;

public abstract class AddEditActivity<T> extends AppCompatActivity {

    public abstract void setFields(Intent intent);
    public abstract String getIdExtra(Intent intent);
    public abstract void setupView();
    public int thisID = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupView();
        Intent intent = getIntent();
        if (intent.hasExtra("id")) {
            Log.d("test", "edit");
            setTitle("Edit");
            thisID = intent.getIntExtra("id", 0);
        } else {
            setTitle("Add");
        }
        setFields(intent);
    }

    public abstract Intent dataFromFields();

    public void save() {
        Intent intent = getIntent();
        Intent data = dataFromFields();

        int id = intent.getIntExtra("id", -1);
        if (id != -1) {
            data.putExtra("id", id);
        }

        setResult(RESULT_OK, data);
        finish();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save:
                save();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
