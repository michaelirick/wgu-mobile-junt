package com.michaelirick.wguscheduler;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.util.Log;

import java.lang.reflect.ParameterizedType;
import java.util.List;

public abstract class Repository<T> {
    public WGUDao<T> dao;
    public LiveData<List<T>> cachedList;

    public void insert(T t) {
        new InsertAsyncTask(dao).execute(t);
    }

    public void update(T t) {
        new UpdateAsyncTask(dao).execute(t);
    }

    public void delete(T t) {
        new DeleteAsyncTask(dao).execute(t);
    }

    public LiveData<T> find(int i ) {
        return dao.find(i);
    }

    public LiveData<List<T>> all() {
        return dao.all();
//        return cachedList;
    }

    public LiveData<List<T>> allFor(int i) {
        if(i == -1)
            return all();
        return dao.allFor(i);
    }

    private static class InsertAsyncTask<T> extends AsyncTask<T, Void, Void> {
        private WGUDao dao;

        private InsertAsyncTask(WGUDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(T... ts) {
            dao.insert(ts[0]);
            return null;
        }
    }


    private static class UpdateAsyncTask<T> extends AsyncTask<T, Void, Void> {
        private WGUDao dao;

        private UpdateAsyncTask(WGUDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(T... ts) {

            Log.d("test", "UpdateAsyncTask(): " + ts[0].toString());
            dao.update(ts[0]);
            Log.d("test", "after dao update");
            return null;
        }
    }

    private static class DeleteAsyncTask<T> extends AsyncTask<T, Void, Void> {
        private WGUDao dao;

        private DeleteAsyncTask(WGUDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(T... ts) {
            dao.delete(ts[0]);
            return null;
        }
    }
}
