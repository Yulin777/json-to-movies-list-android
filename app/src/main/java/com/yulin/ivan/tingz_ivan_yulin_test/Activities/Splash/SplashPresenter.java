package com.yulin.ivan.tingz_ivan_yulin_test.Activities.Splash;

import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProviders;

import com.yulin.ivan.tingz_ivan_yulin_test.Helpers.Threading;
import com.yulin.ivan.tingz_ivan_yulin_test.ModelLayer.Database.MoviesViewModel;
import com.yulin.ivan.tingz_ivan_yulin_test.ModelLayer.Database.MyMovie;
import com.yulin.ivan.tingz_ivan_yulin_test.ModelLayer.Enums.Source;
import com.yulin.ivan.tingz_ivan_yulin_test.R;

import java.io.IOException;
import java.util.List;

import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Ivan Y. on 2019-10-29.
 */

class SplashPresenter {

    abstract static class SplashView extends AppCompatActivity {
        public abstract void dataLoaded();

        public abstract void notifyDataSource(Source source);
    }

    private final SplashView splashView;
    private MoviesViewModel moviesViewModel;

    SplashPresenter(SplashView splashView) {
        this.splashView = splashView;
        this.moviesViewModel = ViewModelProviders.of(splashView).get(MoviesViewModel.class);
    }


    void loadData() {
        LiveData<List<MyMovie>> observer = moviesViewModel.getAllMovies();
        observer.observe(splashView, myMovies -> {
            observer.removeObservers(splashView);

            if (myMovies == null || myMovies.size() == 0) {
                loadJson(json -> persistJson(json, splashView::dataLoaded));
                splashView.notifyDataSource(Source.network);
            } else {
                splashView.notifyDataSource(Source.local);
                splashView.dataLoaded();
            }
        });
    }


    private void persistJson(String json, Runnable finished) {
        Threading.async(() -> {

            clearMovies(() -> {
                moviesViewModel.loadMovies(json);
                Thread.sleep(2000); /* note: mock server delay */
                finished.run();
            });

            return true;
        });
    }

    private void clearMovies(Action finished) throws Exception {
        moviesViewModel.clearMovies();
        finished.run();
    }


    private void loadJson(Consumer<String> finished) {
        Log.d("", "loading json from web");
        Threading.async(this::makeRequest, finished, null);
    }


    private String makeRequest() {
        String result = "";
        try {
            result = run(splashView.getString(R.string.json_url));
        } catch (Exception e) {
            Log.d("", "makeWebCall: Failed!");
            e.printStackTrace();
        }

        return result;
    }

    private String run(String url) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();

        if (response.body() != null) {
            return response.body().string();
        }
        return null;
    }
}
