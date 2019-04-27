package com.michaelirick.wguscheduler.views.terms;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.michaelirick.wguscheduler.R;
import com.michaelirick.wguscheduler.ViewModel;
import com.michaelirick.wguscheduler.adapters.TermAdapter;
import com.michaelirick.wguscheduler.models.Term;

import java.util.List;

public class TermsActivity extends AppCompatActivity {
    private static final int ADD_TERM_REQUEST = 1;
    private ViewModel<Term> vm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms);
//        FloatingActionButton buttonAddNote = findViewById(R.id.button_add_term);
//        buttonAddTerm.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(TermsActivity.this, AddTermActivity.class);
//                startActivityForResult(intent, ADD_TERM_REQUEST);
//            }
//        });

        RecyclerView recyclerView = findViewById(R.id.terms_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final TermAdapter adapter = new TermAdapter();
        recyclerView.setAdapter(adapter);

        vm = ViewModelProviders.of(this).get(TermViewModel.class);
        vm.all().observe(this, new Observer<List<Term>>() {
            @Override
            public void onChanged(@Nullable List<Term> ts) {
                adapter.set(ts);
            }
        });
    }
}
