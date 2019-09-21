package com.michaelirick.wguscheduler;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class ViewModel<T extends Model> extends AndroidViewModel {
    public Repository<T> repository;
    public LiveData<List<T>> cachedList;
    public LiveData<T> cachedFound;

    public ViewModel(Application application) {
        super(application);
    }

    public void insert(T t) {
        repository.insert(t);
    }

    public void update(T t) {
        Log.d("test", "ViewModel::update(): " + t.toLongString());
        repository.update(t);
    }

    public void delete(T t) {
        repository.delete(t);
    }

    public LiveData<T> find(int i) {
        return repository.find(i);
    }

    public LiveData<List<T>> all() {
        return cachedList;
    }

    public LiveData<List<T>> allFor(int i, String filterType) {
        Log.d("test", getClass().getName() + "#allFor: " + i + "::" + filterType);
        return repository.allFor(i, filterType);
    }

    public ArrayList<T> allForSelect() {
        return new ArrayList<T>(repository.all().getValue());
    }
}