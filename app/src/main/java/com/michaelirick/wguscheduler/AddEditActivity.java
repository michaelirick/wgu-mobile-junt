package com.michaelirick.wguscheduler;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public abstract class AddEditActivity<T> extends AppCompatActivity {

    public abstract void setFields(Intent intent);
    public abstract String getIdExtra(Intent intent);
    public abstract void setupView();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupView();
        Intent intent = getIntent();
        if (intent.hasExtra(getIdExtra(intent))) {
            setTitle("Edit");
            setFields(intent);
        } else {
            setTitle("Add");
        }
    }

    public abstract Intent dataFromFields();

    public void save() {
        Intent intent = getIntent();
        Intent data = dataFromFields();

        int id = intent.getIntExtra(getIdExtra(intent), -1);
        if (id != -1) {
            data.putExtra(getIdExtra(intent), id);
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
