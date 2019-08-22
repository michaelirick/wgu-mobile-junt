package com.michaelirick.wguscheduler;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.michaelirick.wguscheduler.views.alerts.AlertIndexActivity;
import com.michaelirick.wguscheduler.views.assessments.AssessmentIndexActivity;
import com.michaelirick.wguscheduler.views.courses.CourseIndexActivity;
import com.michaelirick.wguscheduler.views.courses.CoursesActivity;
import com.michaelirick.wguscheduler.views.terms.TermIndexActivity;
import com.michaelirick.wguscheduler.views.terms.TermsActivity;

public abstract class ApplicationActivity
    extends AppCompatActivity
    implements NavigationView.OnNavigationItemSelectedListener {
    private static final int TERMS_REQUEST = 1;
    private static final int COURSES_REQUEST = 2;
    private static final int ASSESSMENTS_REQUEST = 3;
    private static final int ALERTS_REQUEST = 4;

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

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
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
        int id = item.getItemId();


        if (id == R.id.nav_terms) {
            startView(TermIndexActivity.class, TERMS_REQUEST);
        }
        if (id == R.id.nav_courses) {
            startView(CourseIndexActivity.class, COURSES_REQUEST);
        }
        if(id == R.id.nav_assessments) {
            startView(AssessmentIndexActivity.class, ASSESSMENTS_REQUEST);
        }
        if(id == R.id.nav_alerts) {
            startView(AlertIndexActivity.class, ALERTS_REQUEST);
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void startView(Class klass, int request) {
        startActivityForResult(
                new Intent(this, klass),
                request
        );
    }
}

