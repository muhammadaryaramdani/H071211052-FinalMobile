package com.example.h071211052_finalmobile.listresponses;

import com.example.h071211052_finalmobile.Movie;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieResponse {
    @SerializedName("results")
    private List<Movie> movies;

    public List<Movie> getMovies() {
        return movies;
    }
}
