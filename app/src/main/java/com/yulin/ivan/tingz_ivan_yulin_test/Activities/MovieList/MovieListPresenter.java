package com.yulin.ivan.tingz_ivan_yulin_test.Activities.MovieList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProviders;

import com.yulin.ivan.tingz_ivan_yulin_test.Fragments.MovieDetailsFragment;
import com.yulin.ivan.tingz_ivan_yulin_test.Helpers.CustomItemClickListener;
import com.yulin.ivan.tingz_ivan_yulin_test.ModelLayer.Database.MoviesViewModel;
import com.yulin.ivan.tingz_ivan_yulin_test.ModelLayer.Database.MyMovie;
import com.yulin.ivan.tingz_ivan_yulin_test.R;

import java.util.List;

/**
 * Created by Ivan Y. on 2019-10-29.
 */

public class MovieListPresenter {

    abstract static public class MovieListView extends AppCompatActivity {
        abstract void updateUI(List<MyMovie> myMovies);
    }

    private final MovieListView movieListView;
    private MoviesViewModel moviesViewModel;

    public MovieListPresenter(MovieListView movieListView) {
        this.moviesViewModel = ViewModelProviders.of(movieListView).get(MoviesViewModel.class);
        this.movieListView = movieListView;
    }

    void loadData() {
        moviesViewModel.getAllMovies().observeForever(movieListView::updateUI);
    }

    public LiveData<MyMovie> getMovie(String title) {
        return moviesViewModel.getMovie(title);
    }

    CustomItemClickListener getItemClickListener(MovieViewAdapter adapter) {
        return (v, position) -> openPreviewFragment(position, adapter);
    }

    private void openPreviewFragment(int position, MovieViewAdapter adapter) {
        String title = adapter.getMovies().get(position).title;
        MovieDetailsFragment fragment = MovieDetailsFragment.newInstance(title);

        movieListView.getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(R.anim.fade_in, R.anim.fade_and_slide_down, R.anim.fade_in, R.anim.fade_and_slide_down)
                .replace(R.id.movie_list_activity_container, fragment)
                .addToBackStack(null)
                .commit();

    }
}

