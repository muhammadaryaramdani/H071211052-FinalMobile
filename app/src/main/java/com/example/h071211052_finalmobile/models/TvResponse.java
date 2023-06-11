package com.example.h071211052_finalmobile.models;

import com.example.h071211052_finalmobile.Tv;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TvResponse {
    @SerializedName("results")
    private List<Tv> tvShows;

    public List<Tv> getTvShows() {
        return tvShows;
    }
}

