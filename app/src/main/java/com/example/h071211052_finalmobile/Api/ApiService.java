package com.example.h071211052_finalmobile.Api;

import com.example.h071211052_finalmobile.models.MovieResponse;

import com.example.h071211052_finalmobile.listresponses.TvResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    @GET("movie/now_playing")
    Call<MovieResponse> getMovies(@Query("language") String language,
                                   @Query("page") int page);

    @GET("tv/top_rated")
    Call<TvResponse> getTvshows(@Query("language") String language,
                                         @Query("page") int page);

    @GET("movie/{movie_id}")
    Call<MovieResponse> getMovieById(@Path("movie_id") int movieId,
                                          @Query("language") String language);

    @GET("tv/{tv_id}")
    Call<TvResponse> getTvShowById(@Path("tv_id") int tvId,
                                            @Query("language") String language);
}
