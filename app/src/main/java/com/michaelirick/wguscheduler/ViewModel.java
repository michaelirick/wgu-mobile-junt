package com.michaelirick.wguscheduler;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

public class ViewModel<T> extends AndroidViewModel {
    private Repository<T> repository;
    private LiveData<List<T>> cachedList;

    public ViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
        cachedList = repository.all();
    }

    public void insert(T t) {
        repository.insert(t);
    }

    public void update(T t) {
        repository.update(t);
    }

    public void delete(T t) {
        repository.delete(t);
    }

    public LiveData<List<T>> all() {
        return cachedList;
    }
}