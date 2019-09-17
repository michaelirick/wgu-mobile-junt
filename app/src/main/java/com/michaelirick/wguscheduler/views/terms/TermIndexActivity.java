package com.michaelirick.wguscheduler.views.terms;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.michaelirick.wguscheduler.ApplicationActivity;
import com.michaelirick.wguscheduler.Index;
import com.michaelirick.wguscheduler.R;
import com.michaelirick.wguscheduler.adapters.TermAdapter;
import com.michaelirick.wguscheduler.models.Term;

public class TermIndexActivity extends ApplicationActivity {
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
        index.add_request = Request.ADD_TERM;
        index.edit_request = Request.EDIT_TERM;
        index.create();
    }


    @Override
    public void processResult(Request requestCode, int resultCode, Intent data) {
        Log.d("test", "TermIndexActivity#processResult: " + data);
        index.processResult(requestCode, resultCode, data);
    }
}
