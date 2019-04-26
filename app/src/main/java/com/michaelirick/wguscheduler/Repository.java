package com.michaelirick.wguscheduler;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.lang.reflect.ParameterizedType;
import java.util.List;

public class Repository<T> {
    private WGUDao<T> dao;
    private LiveData<List<T>> cachedList;

    public Repository(Application app) {
        WGUDatabase database = WGUDatabase.getInstance(app);
        ParameterizedType superClass = (ParameterizedType) getClass().getGenericSuperclass();
        dao = database.getDao((Class<T>) superClass.getActualTypeArguments()[0]);
        cachedList = dao.all();
    }

    public void insert(T t) {
        new InsertAsyncTask(dao).execute(t);
    }

    public void update(T t) {
        new UpdateAsyncTask(dao).execute(t);
    }

    public void delete(T t) {
        new DeleteAsyncTask(dao).execute(t);
    }

    public LiveData<List<T>> all() {
        return cachedList;
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
            dao.update(ts[0]);
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
