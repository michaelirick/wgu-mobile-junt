package com.michaelirick.wguscheduler.views.terms;


import android.os.Bundle;

import com.michaelirick.wguscheduler.ApplicationActivity;
import com.michaelirick.wguscheduler.Index;
import com.michaelirick.wguscheduler.R;
import com.michaelirick.wguscheduler.adapters.TermAdapter;
import com.michaelirick.wguscheduler.models.Term;

public class TermIndexActivity extends ApplicationActivity {
    private static final int TERMS_REQUEST = 1;
    private static final int COURSES_REQUEST = 2;
    private static final int ADD_REQUEST = 3;
    private static final int EDIT_REQUEST = 4;
    Index<Term> index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_index);
        setMenu();
        index = new Index<Term>(
                Term.class,
                this,
                TermViewModel.class,
                R.id.button_add_term,
                R.id.terms_recycler_view,
                AddEditTermActivity.class,
                new TermAdapter()
        );
        index.create();
    }
}
