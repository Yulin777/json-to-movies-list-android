package com.yulin.ivan.tingz_ivan_yulin_test.ModelLayer.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

/**
 * Created by Ivan Y. on 2019-10-29.
 */

@Database(entities = {MyMovie.class}, version = 1, exportSchema = false)
public abstract class MyMovieDatabase  extends RoomDatabase {

    public abstract MyMovieDao myMovieDao();

    private static volatile MyMovieDatabase INSTANCE;

    public static MyMovieDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (MyMovieDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            MyMovieDatabase.class, "movies")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
