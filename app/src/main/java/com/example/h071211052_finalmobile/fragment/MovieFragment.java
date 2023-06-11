package com.example.h071211052_finalmobile.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;


import com.example.h071211052_finalmobile.CustomLayoutManager;
import com.example.h071211052_finalmobile.Movie;
import com.example.h071211052_finalmobile.MovieService;
import com.example.h071211052_finalmobile.R;
import com.example.h071211052_finalmobile.adapter.MovieAdapter;
import com.example.h071211052_finalmobile.listresponses.MovieResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieFragment extends Fragment {
    private static final String BASE_URL = "https://api.themoviedb.org/3/";
    private static final String API_KEY = "dad1cd55d3f6d09536f1c6bde1fe8d07";
    private RecyclerView recyclerView;
    private MovieAdapter movieAdapter;

    public MovieFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie, container, false);

        recyclerView = view.findViewById(R.id.rv_movies);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MovieService movieService = retrofit.create(MovieService.class);

        Call<MovieResponse> call = movieService.getNowPlayingMovies(API_KEY);

        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if (response.isSuccessful()) {
                    MovieResponse movieResponse = response.body();
                    List<Movie> movies = movieResponse.getMovies();
                    movieAdapter = new MovieAdapter(movies);
                    recyclerView.setAdapter(movieAdapter);

                    CustomLayoutManager layoutManager = new CustomLayoutManager(getContext(), 2);
                    recyclerView.setLayoutManager(layoutManager);

                } else {
                    Toast.makeText(getActivity(), "Error: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Toast.makeText(getActivity(), "Failure: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}

