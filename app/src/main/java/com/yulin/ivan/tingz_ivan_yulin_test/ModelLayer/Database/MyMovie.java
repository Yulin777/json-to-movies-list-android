package com.yulin.ivan.tingz_ivan_yulin_test.ModelLayer.Database;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.TypeConverters;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Ivan Y. on 2019-10-29.
 */

@Entity(tableName = "movies", primaryKeys = {"title"})
@TypeConverters({Converters.class})
public class MyMovie {

    @NonNull
    public String title;
    public String image;
    public double rating;
    public int releaseYear;
    public ArrayList<String> genres;

    public MyMovie() {
    }

    public MyMovie(JSONObject movieJson) throws JSONException {
        this.title = movieJson.getString("title");
        this.image = movieJson.getString("image");
        this.rating = movieJson.getDouble("rating");
        this.releaseYear = movieJson.getInt("releaseYear");
        this.genres = parseJSONArrayToArrayList(movieJson.getJSONArray("genre"));
    }

    private ArrayList<String> parseJSONArrayToArrayList(JSONArray genres) throws JSONException {
        ArrayList<String> ret = new ArrayList<>();

        for (int i = 0; i < genres.length(); i++) {
            ret.add((String)genres.get(i));
        }
        return ret;
    }
}
