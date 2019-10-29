package com.yulin.ivan.tingz_ivan_yulin_test.ModelLayer.Database;

/**
 * Created by Ivan Y. on 2019-10-29.
 */

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MyMovieDao {

    @Query("SELECT * FROM movies ORDER BY releaseYear DESC")
    LiveData<List<MyMovie>> getAll();

    @Query("DELETE FROM movies")
    void clearMovies();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(MyMovie param);

    @Query("SELECT * FROM movies WHERE title=:title")
    LiveData<MyMovie> getMovie(String title);
}
