package com.michaelirick.wguscheduler.views.terms;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.michaelirick.wguscheduler.Adapter;
import com.michaelirick.wguscheduler.AddEditActivity;
import com.michaelirick.wguscheduler.IndexActivity;
import com.michaelirick.wguscheduler.R;
import com.michaelirick.wguscheduler.ViewModel;
import com.michaelirick.wguscheduler.adapters.TermAdapter;
import com.michaelirick.wguscheduler.models.Term;

import java.util.Date;
import java.util.List;

import static com.michaelirick.wguscheduler.Converters.dateToTimestamp;

public class TermsActivity extends IndexActivity<Term> {
    private static final int ADD_REQUEST = 1;
    private static final int EDIT_REQUEST = 2;

    @Override
    public int getAddButtonId() {
        return R.id.button_add_term;
    }

    @Override
    public int getActivityId() {
        return R.layout.activity_terms;
    }

    @Override
    public Intent createIntent(Term t) {
        Intent intent = new Intent(TermsActivity.this, AddEditTermActivity.class);
        if(t != null) {
            intent.putExtra(AddEditTermActivity.EXTRA_ID, t.getId());
            intent.putExtra(AddEditTermActivity.EXTRA_TITLE, t.getTitle());
            intent.putExtra(AddEditTermActivity.EXTRA_START_DATE, dateToTimestamp(t.getStartDate()));
            intent.putExtra(AddEditTermActivity.EXTRA_END_DATE, dateToTimestamp(t.getEndDate()));
        }

        return intent;
    }

    @Override
    public int getAddRequest() {
        return ADD_REQUEST;
    }

    @Override
    public int getEditRequest() {
        return EDIT_REQUEST;
    }

    @Override
    public int getRecyclerViewId() {
        return R.id.terms_recycler_view;
    }

    @Override
    public Adapter getAdapter() {
        final TermAdapter adapter = new TermAdapter();
        return adapter;
    }

    @Override
    public Class getViewModelClass() {
        return TermViewModel.class;
    }

    @Override
    public Term newElement(Intent data) {
        return new Term(
                data.getStringExtra(AddEditTermActivity.EXTRA_TITLE),
                new Date(), new Date()
        );
    }

    @Override
    public int getElementId(Intent data) {
        return data.getIntExtra(AddEditTermActivity.EXTRA_ID, -1);
    }



}
