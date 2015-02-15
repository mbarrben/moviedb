package com.github.mbarrben.moviedb.model.rest;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

public interface MovieDatabaseAPI {

  @GET("/movie/popular") void getPopularMovies(@Query("api_key") String apiKey, Callback<PopularMoviesApiResponse> callback);
}
