package com.yulin.ivan.tingz_ivan_yulin_test.ModelLayer.Database;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by Ivan Y. on 2019-10-29.
 */

public class MoviesViewModel extends AndroidViewModel {
    private MyMovieRepository myMovieRepository;

    public MoviesViewModel(@NonNull Application application) {
        super(application);
        myMovieRepository = new MyMovieRepository(application);
    }

    public LiveData<List<MyMovie>> getAllMovies() {
        return myMovieRepository.getAllMovies();
    }

    public void clearMovies() {
        myMovieRepository.clearMovies();
    }

    public void loadMovies(String result) throws JSONException {
        JSONArray allMovies = new JSONArray(result);

        for (int i = 0; i < allMovies.length(); i++) {
            JSONObject movieJson = allMovies.getJSONObject(i);
            MyMovie movie = new MyMovie(movieJson);
            myMovieRepository.insert(movie);
        }
    }

    public LiveData<MyMovie> getMovie(String title) {
        return myMovieRepository.getMovie(title);
    }

    public void addMovie(MyMovie movie) {
        myMovieRepository.insert(movie);
    }
}
