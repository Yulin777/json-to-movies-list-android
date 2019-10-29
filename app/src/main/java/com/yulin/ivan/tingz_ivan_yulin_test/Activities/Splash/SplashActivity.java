package com.yulin.ivan.tingz_ivan_yulin_test.Activities.Splash;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.yulin.ivan.tingz_ivan_yulin_test.Activities.MovieList.MovieListActivity;
import com.yulin.ivan.tingz_ivan_yulin_test.ModelLayer.Enums.Source;
import com.yulin.ivan.tingz_ivan_yulin_test.R;

/**
 * Created by Ivan Y. on 2019-10-29.
 */

public class SplashActivity extends SplashPresenter.SplashView {
    private SplashPresenter splashPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        splashPresenter = new SplashPresenter(this);
        loadData();
    }

    private void loadData() {
        splashPresenter.loadData();
    }

    private void startMovieListActivity() {
        startActivity(new Intent(this, MovieListActivity.class));
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        finish();
    }

    @Override
    public void notifyDataSource(Source source) {
        String message = String.format("Loaded Data from %s", source.name());
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void dataLoaded() {
        startMovieListActivity();
    }
}
