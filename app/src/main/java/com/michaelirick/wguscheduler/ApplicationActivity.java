package com.michaelirick.wguscheduler;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.michaelirick.wguscheduler.views.alerts.AlertIndexActivity;
import com.michaelirick.wguscheduler.views.assessments.AssessmentIndexActivity;
import com.michaelirick.wguscheduler.views.courses.CourseIndexActivity;
import com.michaelirick.wguscheduler.views.terms.TermIndexActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public abstract class ApplicationActivity
    extends AppCompatActivity
    implements NavigationView.OnNavigationItemSelectedListener {

    public enum Request {
        COURSES, ADD_COURSE, EDIT_COURSE,
        TERMS, ADD_TERM, EDIT_TERM,
        ALERTS, ADD_ALERTS, EDIT_ALERTS,
        ASSESSMENTS, ADD_ASSESSMENT, EDIT_ASSESSMENT,
        NOTES, ADD_NOTE, EDIT_NOTE
    };

    private List<RequestHandler> requestMappings;




    public void setMenu() {
        Toolbar toolbar = findViewById(R.id.toolbar);
//        if(getSupportActionBar() == null) {
//            setSupportActionBar(toolbar);
//            DrawerLayout drawer = findViewById(R.id.drawer_layout);
//            NavigationView navigationView = findViewById(R.id.nav_view);
//            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
//                    this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//            drawer.addDrawerListener(toggle);
//            toggle.syncState();
//            navigationView.setNavigationItemSelectedListener(this);
//        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        if(navigationView != null)
            navigationView.setNavigationItemSelectedListener(this);

        setRequestMappings();
    }

    private void setRequestMappings() {
        requestMappings = new ArrayList<>(
                Arrays.asList(
                        new RequestHandler(this, R.id.nav_terms, TermIndexActivity.class, Request.TERMS),
                        new RequestHandler(this, R.id.nav_courses, CourseIndexActivity.class, Request.COURSES),
                        new RequestHandler(this, R.id.nav_assessments, AssessmentIndexActivity.class, Request.ASSESSMENTS),
                        new RequestHandler(this, R.id.nav_alerts, AlertIndexActivity.class, Request.ALERTS)
                )
        );

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer != null && drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        final int id = item.getItemId();
        requestMappings.forEach(new Consumer<RequestHandler>() {
            @Override
            public void accept(RequestHandler requestHandler) {
                requestHandler.check(id);
            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void startView(Class klass, Request request) {
        startActivityForResult(
                new Intent(this, klass),
                request.ordinal()
        );
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d("test", "ApplicationActivity#onActivityResult");
        processResult(Request.values()[requestCode], resultCode, data);
    }

    public void processResult(Request requestCode, int resultCode, Intent data) {
        Log.d("test", "processResult not implemented");
    }

    public void debug(String method, String data) {
        Log.d("test", this.getClass().getName() + method + ": " + data);
    }
}

