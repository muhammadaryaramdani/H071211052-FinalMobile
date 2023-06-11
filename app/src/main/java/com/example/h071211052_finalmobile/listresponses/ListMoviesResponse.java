package com.example.h071211052_finalmobile.listresponses;

import com.example.h071211052_finalmobile.models.MoviesResponse;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListMoviesResponse {
    @SerializedName("results")
    private List<MoviesResponse> moviesData;

    @SerializedName("total_pages")
    private int totalPages;

    public List<MoviesResponse> getMoviesData() {
        return moviesData;
    }

    public int getTotalPages() {
        return totalPages;
    }
}

