package com.yulin.ivan.tingz_ivan_yulin_test.ModelLayer.Database;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

/**
 * Created by Ivan Y. on 2019-10-29.
 */

public class MyMovieRepository {
    private final Context context;
    private MyMovieDao myMovieDao;

    public MyMovieRepository(Application application) {
        this.context = application.getApplicationContext();
        MyMovieDatabase myMovieDatabase = MyMovieDatabase.getDatabase(application);
        myMovieDao = myMovieDatabase.myMovieDao();
    }

    public LiveData<List<MyMovie>> getAllMovies() {
        return myMovieDao.getAll();
    }

    public void clearMovies() {
        myMovieDao.clearMovies();
    }

    public void insert(MyMovie movie) {
        new insertAsyncTask(myMovieDao).execute(movie);
    }

    public LiveData<MyMovie> getMovie(String title) {
        return myMovieDao.getMovie(title);
    }

    private static class insertAsyncTask extends AsyncTask<MyMovie, Void, Void> {

        private MyMovieDao mAsyncTaskDao;

        insertAsyncTask(MyMovieDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final MyMovie... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}
