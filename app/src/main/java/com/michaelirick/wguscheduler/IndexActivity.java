package com.michaelirick.wguscheduler;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.michaelirick.wguscheduler.adapters.TermAdapter;
import com.michaelirick.wguscheduler.models.Term;
import com.michaelirick.wguscheduler.views.terms.AddEditTermActivity;
import com.michaelirick.wguscheduler.views.terms.TermViewModel;
import com.michaelirick.wguscheduler.views.terms.TermsActivity;

import java.util.List;

public abstract class IndexActivity<T extends Model> extends AppCompatActivity {
    private ViewModel vm;

    public abstract int getAddButtonId();
    public abstract int getActivityId();
    public abstract Intent createIntent(T t);
    public abstract int getAddRequest();
    public abstract int getEditRequest();
    public abstract int getRecyclerViewId();
    public abstract Adapter getAdapter();
    public abstract Class getViewModelClass();
    public abstract T newElement(Intent data);
    public abstract int getElementId(Intent data);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getActivityId());
        FloatingActionButton buttonAdd = findViewById(getAddButtonId());
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = createIntent(null);
                startActivityForResult(intent, getAddRequest());
            }
        });

        RecyclerView recyclerView = findViewById(getRecyclerViewId());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        final Adapter adapter = getAdapter();
        recyclerView.setAdapter(adapter);

        vm = (ViewModel) ViewModelProviders.of(this).get(getViewModelClass());
        vm.all().observe(this, new Observer<List<Term>>() {
            @Override
            public void onChanged(@Nullable List<Term> ts) {
                adapter.set(ts);
            }
        });

        //setClickListener(adapter);
        adapter.setOnItemClickListener(new Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(Object o) {
                Log.d("TEST","OnItemClick");
                Intent intent = createIntent((T)o);
                startActivityForResult(intent,getEditRequest());
            }
        });

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == getAddRequest() && resultCode == RESULT_OK) {
            T t = newElement(data);
            vm.insert(t);
            Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
        } else if (requestCode == getEditRequest() && resultCode == RESULT_OK) {
            int id = getElementId(data);

            if (id == -1) {
                Toast.makeText(this, "Cannot be updated", Toast.LENGTH_SHORT).show();
                return;
            }

            T t = newElement(data);
            t.setId(id);
            vm.update(t);

            Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Not saved", Toast.LENGTH_SHORT).show();
        }
    }
}
