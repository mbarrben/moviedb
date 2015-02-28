package com.github.mbarrben.moviedb.model.rest;

import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

public interface MovieDatabaseAPI {

  @GET("/movie/popular") Observable<PopularMoviesApiResponse> getPopularMovies(@Query("api_key") String apiKey);
}
