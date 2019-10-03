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
import android.widget.LinearLayout;

import com.michaelirick.wguscheduler.models.Alert;
import com.michaelirick.wguscheduler.views.courses.AddEditCourseActivity;

import java.util.Calendar;
import java.util.Date;

public abstract class AddEditActivity<T> extends ApplicationActivity{

    public abstract void setFields(Intent intent);
    public abstract String getIdExtra(Intent intent);
    public abstract void setupView();
    public int thisID = 0;
    public int filterID = 0;
    public String filterType = "";
    public T object;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        debug("#onCreate", "" + intent.toUri(0));
        filterID = intent.getIntExtra("filterID", 0);
        thisID = intent.getIntExtra("id", 0);
        debug("#onCreate", "thisID: " + thisID);
        setupView();
        if (thisID != 0) {
            setTitle("Edit");
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

    public boolean canDelete() {
        return true;
    }

    public void showDeleteAlert() {

    }

    public void delete() {
        if(!canDelete()) {
            showDeleteAlert();
            return;
        }
        Intent data = getIntent();
        data.putExtra("delete", true);
        debug("#delete", data.toUri(0));
        setResult(RESULT_OK, data);
        finish();
    }

    public void addAlertButton(final ApplicationActivity app, int id, final String title, final String message, final Date date) {
        Button alertButton =findViewById(id);
        alertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Alert alert = new Alert(title, message, date);
                alert.create(app, ApplicationReceiver.class);
            }

        });
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
            case R.id.delete:
                delete();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        Log.d("test", "AddEditActivity#onActivityResult");
//        processResult(requestCode, resultCode, data);
//    }

    public void processResult(Request requestCode, int resultCode, Intent data) {
    }

    public void setupPanel(int toggle, int body) {
        CollapsePanel panel = new CollapsePanel(
                (Button) findViewById(toggle),
                (LinearLayout) findViewById(body)
        );
        panel.create();
        panel.toggleView(); // start closed

    }
}
