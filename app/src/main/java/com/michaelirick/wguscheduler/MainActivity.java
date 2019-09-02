package com.michaelirick.wguscheduler;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.Button;

import com.michaelirick.wguscheduler.models.Course;
import com.michaelirick.wguscheduler.models.Term;
import com.michaelirick.wguscheduler.views.courses.CoursesActivity;
import com.michaelirick.wguscheduler.views.terms.TermViewModel;
import com.michaelirick.wguscheduler.views.terms.TermsActivity;

import java.util.Date;

import static com.michaelirick.wguscheduler.Converters.fromTimestamp;
import static com.michaelirick.wguscheduler.Converters.now;

public class MainActivity extends ApplicationActivity {
    private static final int TERMS_REQUEST = 1;
    private static final int COURSES_REQUEST = 2;
    public Button seedData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setMenu();
        seedData = findViewById(R.id.seed_data);
        seedData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Date now = fromTimestamp(now());
                Term t1 = new Term(
                        "Term 1",
                        now,
                        now
                );
                TermViewModel tvm = new TermViewModel(getApplication());
                tvm.insert(t1);
//                Course c1 = new Course(
//                        "Course 1",
//                        now,
//                        now,
//                        "started",
//                        "Cool Mentor",
//                        "1112223344",
//                        "cool@wgu.edu",
//                        t1.id
//                );
            }
        });
    }
}
