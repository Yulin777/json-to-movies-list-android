package com.yulin.ivan.tingz_ivan_yulin_test.Activities.MovieList;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.willy.ratingbar.ScaleRatingBar;
import com.yulin.ivan.tingz_ivan_yulin_test.ModelLayer.Database.MyMovie;
import com.yulin.ivan.tingz_ivan_yulin_test.R;

/**
 * Created by Ivan Y. on 2019-10-29.
 */

class MovieViewHolder extends RecyclerView.ViewHolder {

    private Context context;

    private ImageView image;
    private TextView title;
    private TextView year;
    private TextView genre;
    private ScaleRatingBar rating;

    MovieViewHolder(@NonNull View itemView) {
        super(itemView);
        this.context = itemView.getContext();

        this.image = itemView.findViewById(R.id.movie_item_image);
        this.title = itemView.findViewById(R.id.movie_item_title);
        this.year = itemView.findViewById(R.id.movie_item_year);
        this.genre = itemView.findViewById(R.id.movie_item_genre);
        this.rating = itemView.findViewById(R.id.movie_item_rating);
    }

    void configureView(MyMovie myMovie) {
        Glide.with(context)
                .load(myMovie.image)
                .transition(DrawableTransitionOptions.withCrossFade().crossFade(1000))
                .into(this.image);

        String genres = "";
        for (String genre : myMovie.genres) {
            genres = genres.concat(genre + ", ");
        }
        genres = genres.substring(0, genres.length() - 2); //remove the comma

        title.setText(myMovie.title);
        year.setText(String.valueOf(myMovie.releaseYear));
        genre.setText(genres);
        rating.setRating((float) myMovie.rating / 2);
    }
}
