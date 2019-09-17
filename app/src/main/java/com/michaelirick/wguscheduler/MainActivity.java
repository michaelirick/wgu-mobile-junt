package com.michaelirick.wguscheduler;

import android.os.Bundle;
import android.view.View;

import android.widget.Button;

import com.michaelirick.wguscheduler.models.Term;
import com.michaelirick.wguscheduler.views.terms.TermViewModel;

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
