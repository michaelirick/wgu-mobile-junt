package com.michaelirick.wguscheduler;

import android.app.Activity;
import android.app.Application;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class ModelSpinner<T extends Model> {
    public ViewModel<T> items;
    public Spinner select;
    public AppCompatActivity owner;

    public ModelSpinner(AppCompatActivity owner, Spinner select, ViewModel<T> items, final int selected) {
        this.select = select;
        this.items = items;
        this.owner = owner;
        final ArrayAdapter<T> adapter = new ArrayAdapter<T>(
                owner.getApplicationContext(),
                R.layout.support_simple_spinner_dropdown_item,
                new ArrayList<T>()
        );
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        select.setAdapter(adapter);
        items.all().observe(owner, new Observer<List<T>>() {
            @Override
            public void onChanged(@Nullable List<T> ts) {
                adapter.clear();
                adapter.addAll(ts);
                updateSelected(selected);
            }
        });
    }

    public T getSelectedItem() {
        return (T) select.getSelectedItem();
    }

    public void updateSelected(int selected) {
        items.find(selected).observe(owner, new Observer<T>() {
            @Override
            public void onChanged(@Nullable T t) {
                if(t == null)
                    return;
                int selection = 0;
                for(int i = 0; i < select.getAdapter().getCount(); i++) {
                    T ti = (T) select.getAdapter().getItem(i);
                    if(t.id == ti.id) {
                        selection = i;
                        break;
                    }
                }
                select.setSelection(selection);
            }
        });
    }
}
