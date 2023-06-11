package com.example.h071211052_finalmobile;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.h071211052_finalmobile.db.DatabaseHelper;
import com.example.h071211052_finalmobile.models.Favorite;

public class DetailsActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;
    private ImageView latar, back, favorit, poster;
    private TextView judul, rating, sinopsis;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        latar = findViewById(R.id.latar_belakang);
        back = findViewById(R.id.back);
        favorit = findViewById(R.id.love);
        poster = findViewById(R.id.poster);
        judul = findViewById(R.id.judul);
        rating = findViewById(R.id.rating);
        sinopsis = findViewById(R.id.sinopsis);
        dbHelper = new DatabaseHelper(this);

        back.setOnClickListener(view -> {
            onBackPressed();
        });

        Intent intent = getIntent();
        if (intent.getParcelableExtra("movie") != null) {
            Movie movie = intent.getParcelableExtra("movie");
            String posterUrl = "https://image.tmdb.org/t/p/w300_and_h450_bestv2/" + movie.getPosterPath();
            String backdropUrl = "https://image.tmdb.org/t/p/w300_and_h450_bestv2/" + movie.getBackdropUrl();
            judul.setText(movie.getTitle());
            rating.setText(movie.getVoteAverage().toString());
            Glide.with(this)
                    .load(posterUrl)
                    .into(poster);
            Glide.with(this)
                    .load(backdropUrl)
                    .into(latar);
            sinopsis.setText(movie.getOverview());

            if (dbHelper.isFavorites(movie.getTitle())) {
                favorit.setImageResource(R.drawable.baseline_fav_border_19);
            }

            favorit.setOnClickListener(view ->  {
                if (!dbHelper.isFavorites(movie.getTitle())) {
                    favorit.setImageResource(R.drawable.baseline_fav_border_19);
                    addToFavorites(movie.getId(), movie.getOverview(), posterUrl, movie.getReleaseDate(), movie.getTitle(), movie.getVoteAverage(), backdropUrl);
                } else {
                    favorit.setImageResource(R.drawable.baseline_fav);
                    deleteFromFavorites(movie.getTitle());
                }
            });
        } else if (intent.getParcelableExtra("show") != null) {
            Tv show = intent.getParcelableExtra("show");
            String posterUrl = "https://image.tmdb.org/t/p/w300_and_h450_bestv2/" + show.getPosterPath();
            String backdropUrl = "https://image.tmdb.org/t/p/w300_and_h450_bestv2/" + show.getBackdropUrl();
            judul.setText(show.getTitle());
            rating.setText(show.getVoteAverage().toString());
            Glide.with(this)
                    .load(posterUrl)
                    .into(poster);
            Glide.with(this)
                    .load(backdropUrl)
                    .into(latar);
            sinopsis.setText(show.getOverview());

            if (dbHelper.isFavorites(show.getTitle())) {
                favorit.setImageResource(R.drawable.baseline_fav);
            }

            favorit.setOnClickListener(view -> {
                if (!dbHelper.isFavorites(show.getTitle())) {
                    favorit.setImageResource(R.drawable.baseline_fav);
                    addToFavorites(show.getId(), show.getOverview(), posterUrl, show.getTitle(), show.getTitle(), show.getVoteAverage(), backdropUrl);
                } else {
                    favorit.setImageResource(R.drawable.baseline_fav);
                    deleteFromFavorites(show.getTitle());
                }
            });
        }
        else {
            Favorite favorite = intent.getParcelableExtra("favorite");
            String posterUrl = "https://image.tmdb.org/t/p/w300_and_h450_bestv2/" + favorite.getPosterPath();
            String backdropUrl = "https://image.tmdb.org/t/p/w300_and_h450_bestv2/" + favorite.getBackdropUrl();
            judul.setText(favorite.getTitle());
            rating.setText(favorite.getVoteAverage().toString());
            Glide.with(this)
                    .load(posterUrl)
                    .into(poster);
            Glide.with(this)
                    .load(backdropUrl)
                    .into(latar);
            sinopsis.setText(favorite.getOverview());

            if (dbHelper.isFavorites(favorite.getTitle())) {
                favorit.setImageResource(R.drawable.baseline_fav);
            }

            favorit.setOnClickListener(view -> {
                if (!dbHelper.isFavorites(favorite.getTitle())) {
                    favorit.setImageResource(R.drawable.baseline_fav);
                    addToFavorites(favorite.getId(), favorite.getOverview(), posterUrl, favorite.getTitle(), favorite.getTitle(), favorite.getVoteAverage(), backdropUrl);
                } else {
                    favorit.setImageResource(R.drawable.baseline_fav_border_19);
                    deleteFromFavorites(favorite.getTitle());
                }
            });
        }
    }

    private void addToFavorites(int id, String overview, String posterUrl, String releaseDate, String title, double voteAverage, String backdropUrl) {
        Favorite favorite = new Favorite(id, overview, posterUrl, releaseDate, title, voteAverage, backdropUrl);
        long result = dbHelper.insertFavorite(favorite);
        if (result != -1) {
            Toast.makeText(this, "Movie / Show added to favorites", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Failed to add to favorite", Toast.LENGTH_SHORT).show();
        }
    }
    private void deleteFromFavorites(String nama) {
        long result = dbHelper.deleteFavorite(nama);
        if (result != -1) {
            Toast.makeText(this, "Movie / Show Deleted From favorites", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Failed to delete movie", Toast.LENGTH_SHORT).show();
        }
    }
}
