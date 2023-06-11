package com.example.h071211052_finalmobile;



import com.example.h071211052_finalmobile.models.MoviesResponse;
import com.google.gson.annotations.SerializedName;

public class MoviesDataResponse {

    @SerializedName("results")
    private MoviesResponse moviesData;
    public MoviesResponse getMoviesData() {
        return moviesData;
    }
}
