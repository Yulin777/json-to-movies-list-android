package com.yulin.ivan.tingz_ivan_yulin_test.Activities.MovieList;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.yulin.ivan.tingz_ivan_yulin_test.ModelLayer.Database.MyMovie;
import com.yulin.ivan.tingz_ivan_yulin_test.R;

import java.util.List;

/**
 * Created by Ivan Y. on 2019-10-29.
 */
public class MovieListActivity extends MovieListPresenter.MovieListView {

    private MovieListPresenter movieListPresenter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);

        movieListPresenter = new MovieListPresenter(this);
        setupUI();
        loadData();
    }

    private void loadData() {
        movieListPresenter.loadData();
    }

    private void setupUI() {
        LinearLayoutManager manager = new LinearLayoutManager(this);

        recyclerView = findViewById(R.id.movie_recycler_view);
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);

        initializeListView();
    }

    private void initializeListView() {
        MovieViewAdapter adapter = new MovieViewAdapter();
        adapter.setItemClickListener(movieListPresenter.getItemClickListener(adapter));
        recyclerView.setAdapter(adapter);
    }

    @Override
    void updateUI(List<MyMovie> myMovies) {

        MovieViewAdapter adapter = (MovieViewAdapter) recyclerView.getAdapter();

        if (adapter != null) {
            adapter.setMovies(myMovies);
            adapter.notifyDataSetChanged();
        }
    }
}
