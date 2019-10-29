package com.yulin.ivan.tingz_ivan_yulin_test.Activities.MovieList;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yulin.ivan.tingz_ivan_yulin_test.Helpers.CustomItemClickListener;
import com.yulin.ivan.tingz_ivan_yulin_test.ModelLayer.Database.MyMovie;
import com.yulin.ivan.tingz_ivan_yulin_test.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ivan Y. on 2019-10-29.
 */

class MovieViewAdapter extends RecyclerView.Adapter<MovieViewHolder> {
    private List<MyMovie> movies;
    private CustomItemClickListener clickListener;

    MovieViewAdapter() {
        this.movies = new ArrayList<>();
    }

    void setMovies(List<MyMovie> movies) {
        this.movies = movies;
    }

    List<MyMovie> getMovies() {
        return movies;
    }

    void setItemClickListener(CustomItemClickListener listener) {
        this.clickListener = listener;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View movieItemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent, false);
        MovieViewHolder movieViewHolder = new MovieViewHolder(movieItemView);
        movieItemView.setOnClickListener(v -> clickListener.onItemClick(v, movieViewHolder.getAdapterPosition()));

        TranslateAnimation animation = new TranslateAnimation(0, 0, 50, 0);
        animation.setDuration(600);
        movieItemView.startAnimation(animation);

        return movieViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        holder.configureView(movies.get(position));
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

}
