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
import com.example.h071211052_finalmobile.R;
import com.example.h071211052_finalmobile.Tv;

import java.util.List;
public class TvShowAdapter extends RecyclerView.Adapter<TvShowAdapter.TvViewHolder> {
    private List<Tv> tvShows;

    public TvShowAdapter(List<Tv> tvShows) {
        this.tvShows = tvShows;
    }

    @NonNull
    @Override
    public TvViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.tv_shows, parent, false);
        return new TvViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TvViewHolder holder, int position) {
        Context context = holder.itemView.getContext();
        Tv tvShow = tvShows.get(position);
        holder.setData(tvShow, context);
    }

    @Override
    public int getItemCount() {
        return tvShows != null ? tvShows.size() : 0;
    }

    static class TvViewHolder extends RecyclerView.ViewHolder {
        private ImageView poster;
        private TextView judul;
        private TextView tahun;

        public TvViewHolder(@NonNull View itemView) {
            super(itemView);
            poster = itemView.findViewById(R.id.iv_poster);
            judul = itemView.findViewById(R.id.tv_title);
            tahun = itemView.findViewById(R.id.tv_year_date);
        }

        public void setData(Tv tvShow, Context context) {
            String title = tvShow.getTitle();
            String year = tvShow.getFirstAirDate();
            String gambar = "https://www.themoviedb.org/t/p/w300_and_h450_bestv2/" + tvShow.getPosterPath();
            judul.setText(title);
            tahun.setText(year);
            Glide.with(context)
                    .load(gambar)
                    .into(poster);
            this.itemView.setOnClickListener(view ->  {
                Intent intent = new Intent(itemView.getContext(), DetailsActivity.class);
                intent.putExtra("show", tvShow);
                itemView.getContext().startActivity(intent);
            });
        }
    }
}




