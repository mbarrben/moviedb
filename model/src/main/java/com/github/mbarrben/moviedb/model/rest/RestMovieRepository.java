package com.github.mbarrben.moviedb.model.rest;

import com.github.mbarrben.moviedb.model.MovieRepository;
import com.github.mbarrben.moviedb.model.entities.Movie;
import retrofit.RestAdapter;
import rx.Observable;

public final class RestMovieRepository implements MovieRepository {

  public static final String MOVIE_DB_HOST = "http://api.themoviedb.org/3/";

  private final MovieDatabaseAPI api;
  private final String apiKey;

  public RestMovieRepository(boolean debug, String apiKey) {
    this.apiKey = apiKey;

    RestAdapter adapter = new RestAdapter.Builder().setEndpoint(MOVIE_DB_HOST)
        .setLogLevel(debug ? RestAdapter.LogLevel.FULL : RestAdapter.LogLevel.NONE)
        .build();

    api = adapter.create(MovieDatabaseAPI.class);
  }

  @Override
  public Observable<Movie.List> getMovies() {
    return api.getPopularMovies(apiKey)
        .map(PopularMoviesApiResponse::getResults);
  }
}
