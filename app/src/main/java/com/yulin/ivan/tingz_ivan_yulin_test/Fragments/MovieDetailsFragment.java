package com.yulin.ivan.tingz_ivan_yulin_test.Fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.willy.ratingbar.ScaleRatingBar;
import com.yulin.ivan.tingz_ivan_yulin_test.Activities.MovieList.MovieListPresenter;
import com.yulin.ivan.tingz_ivan_yulin_test.ModelLayer.Database.MyMovie;
import com.yulin.ivan.tingz_ivan_yulin_test.R;


/**
 * Created by Ivan Y. on 2019-10-29.
 */
public class MovieDetailsFragment extends Fragment {

    private static final String ARG_PARAM1 = "title";
    private String title;
    private MovieListPresenter.MovieListView activity;
    private ImageView imageView;
    private TextView titleView;
    private TextView yearView;
    private TextView genreView;
    private ScaleRatingBar ratingView;

    public MovieDetailsFragment() {
        // Required empty public constructor
    }

    public static MovieDetailsFragment newInstance(String title) {

        MovieDetailsFragment fragment = new MovieDetailsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, title);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            title = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_details, container, false);

        getViews(view);
        setViewsData();
        setSwipeDownToExitGesture();

        view.setOnClickListener(view1 -> {
            //nada!!
        });

        return view;
    }

    private void getViews(View view) {
        this.imageView = view.findViewById(R.id.fragment_movie_image);
        this.titleView = view.findViewById(R.id.fragment_movie_title);
        this.yearView = view.findViewById(R.id.fragment_movie_year);
        this.genreView = view.findViewById(R.id.fragment_movie_genre);
        this.ratingView = view.findViewById(R.id.fragment_movie_rating);
    }

    @SuppressLint("ClickableViewAccessibility")
    private void setSwipeDownToExitGesture() {
        imageView.setOnTouchListener((view12, motionEvent) -> {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                activity.onBackPressed(); //close self
                return true;
            }
            return false;
        });
    }

    private void setViewsData() {

        MovieListPresenter movieListPresenter = new MovieListPresenter(activity);
        movieListPresenter.getMovie(title).observe(activity, myMovie -> {

            setImageView(myMovie);
            setGenreView(myMovie);
            titleView.setText(title);
            yearView.setText(String.valueOf(myMovie.releaseYear));
            ratingView.setRating((float) myMovie.rating / 2);

        });
    }

    private void setGenreView(MyMovie myMovie) {
        String genres = "";

        for (String genre : myMovie.genres) {
            genres = genres.concat(genre + ", ");
        }
        if (genres.length() > 2)
            genres = genres.substring(0, genres.length() - 2);

        genreView.setText(genres);
    }

    private void setImageView(MyMovie myMovie) {

        TranslateAnimation animation = new TranslateAnimation(0, 0, 100, 0);
        animation.setDuration(600);
        imageView.startAnimation(animation);
        Glide.with(activity)
                .load(myMovie.image)
                .transition(DrawableTransitionOptions.withCrossFade().crossFade(1000))
                .into(imageView);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.activity = (MovieListPresenter.MovieListView) context;
    }


}
