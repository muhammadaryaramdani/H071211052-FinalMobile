package com.example.h071211052_finalmobile.adapter;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.h071211052_finalmobile.DetailsActivity;
import com.example.h071211052_finalmobile.Movie;
import com.example.h071211052_finalmobile.R;

import java.util.List;
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private List<Movie> movies;

    public MovieAdapter(List<Movie> movies) {
        this.movies = movies;

    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_movie, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Context context = holder.itemView.getContext();
        Movie movie = movies.get(position);
        holder.setData(movie, context);
    }

    @Override
    public int getItemCount() {
        return movies != null ? movies.size() : 0;
    }

    static class MovieViewHolder extends RecyclerView.ViewHolder {
        private ImageView poster;
        private TextView judul;
        private TextView tahun;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            poster = itemView.findViewById(R.id.iv_poster);
            judul = itemView.findViewById(R.id.tv_title);
            tahun = itemView.findViewById(R.id.tv_year_date);
        }

        public void setData(Movie movie, Context context) {

            String title = movie.getTitle();
            String year = movie.getReleaseDate();
            String gambar = "https://image.tmdb.org/t/p/w300_and_h450_bestv2/" + movie.getPosterPath();
            judul.setText(title);
            tahun.setText(year);
            Glide.with(context)
                    .load(gambar)
                    .into(poster);
            this.itemView.setOnClickListener(view ->  {
                Intent intent = new Intent(itemView.getContext(), DetailsActivity.class);
                intent.putExtra("movie", movie);
                itemView.getContext().startActivity(intent);
            });
        }
    }
}