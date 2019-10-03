package com.michaelirick.wguscheduler;

import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.michaelirick.wguscheduler.ApplicationActivity;

import com.michaelirick.wguscheduler.models.Term;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import static android.app.Activity.RESULT_OK;

public class Index<T extends Model> {
    public Class<T> klass;
    public FragmentActivity activity;
    public ViewModel vm;
    public int addButtonId;
    public int activityId;
    public int recyclerViewId;
    public Adapter adapter;
    public Class viewModelClass;
    public Class addEditClass;
    public int filterId = -1;
    public String filterType = "";
    public ApplicationActivity.Request add_request;
    public ApplicationActivity.Request edit_request;

    public Index() {

    }

    public Index(Class k, FragmentActivity a, Class v, int b, int r, Class ae, Adapter ad) {
        klass = k;
        activity = a;
        viewModelClass = v;
        addButtonId = b;
        recyclerViewId = r;
        addEditClass = ae;
        adapter = ad;
    }

    public void setOnClick() {
        adapter.setOnItemClickListener(new Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(Object o) {
                Intent intent = Model.createIntent(
                        activity, addEditClass, (T) o
                );
                intent.putExtra("filterID", filterId);
                intent.putExtra("filterType", filterType);
                activity.startActivityForResult(intent, edit_request.ordinal());
            }
        });
    }

    public T newElement(Intent data) {
        Class[] args = new Class[2];
        args[0] = Intent.class;
        args[1] = Class.class;
        try {
            Log.d("test", "newElement(" + klass.getName() + "): " + data.getExtras().toString());
            T t = klass.getDeclaredConstructor(args).newInstance(data, addEditClass);
            return t;
        } catch (IllegalAccessException e) {
            return null;
        } catch (InstantiationException e) {
            e.printStackTrace();
            return null;
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            return null;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void setAddButton() {
        if(addButtonId == 0)
            return;
        FloatingActionButton buttonAdd = activity.findViewById(addButtonId);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = Model.createIntent(
                        activity, addEditClass, null
                );
                intent.putExtra("filterID", filterId);
                intent.putExtra("filterType", filterType);
                Log.d("test", "Index#setAddButton: " + intent.getExtras().toString());
                activity.startActivityForResult(intent, add_request.ordinal());
            }
        });
    }

    public void setRecyclerView() {
        RecyclerView recyclerView = activity.findViewById(recyclerViewId);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        updateList();

    }

    public void updateList() {
        vm = (ViewModel) ViewModelProviders.of(activity).get(viewModelClass);
        Log.d("test", getClass().getName() + "<" + klass.getName() + ">" + "#updateList(" + filterId + ")");
        vm.allFor(filterId, filterType).observe(activity, new Observer<List<T>>() {
            @Override
            public void onChanged(@Nullable List<T> ts) {
                Log.d("test", klass.getName() + " list changed: " + ts);
                adapter.set(ts);
            }
        });
    }

    public void create() {
        setAddButton();
        setRecyclerView();
        setOnClick();
    }

    public T processResult(ApplicationActivity.Request requestCode, int resultCode, Intent data) {
        Log.d("test", "Index<" + klass.getName() + ">#processResult: " + requestCode + ", " + resultCode + ": " + (data == null ? "null data" : data.toUri(0)));
        T r = null;
        if(data != null && data.getBooleanExtra("delete", false)) {
            T t = newElement(data);
             vm.delete(t);
        } else if (requestCode == add_request && resultCode == RESULT_OK) {
            T t = newElement(data);
            vm.insert(t);
            //Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
            r = t;
        } else if (requestCode == edit_request
                && resultCode == RESULT_OK) {
            int id = data.getIntExtra("id", -1);
            if (id == -1) {
                Log.d("test", "id == -1, cannot update");
                //Toast.makeText(this, "Cannot be updated", Toast.LENGTH_SHORT).show();
                return null;
            }

            T t = newElement(data);
//            t.setId(id);
            vm.update(t);
            r = t;
            //Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show();
        } else {
            //Toast.makeText(this, "Not saved", Toast.LENGTH_SHORT).show();
        }
        updateList();
        return r;
    }

    public boolean isEmpty() {
        return adapter.getItemCount() == 0;
    }
}
