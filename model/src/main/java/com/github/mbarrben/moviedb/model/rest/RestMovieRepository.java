package com.github.mbarrben.moviedb.model.rest;

import com.github.mbarrben.moviedb.common.RestBus;
import com.github.mbarrben.moviedb.model.MovieRepository;
import com.github.mbarrben.moviedb.model.entities.Movie;
import com.squareup.otto.Bus;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public final class RestMovieRepository implements MovieRepository {

    public static final String MOVIE_DB_HOST = "http://api.themoviedb.org/3/";
    public static final String API_KEY = "87a901020f496977f9d6d508c5d186ec";

    private final MovieDatabaseAPI api;
    private final Bus restBus;

    public RestMovieRepository(@RestBus Bus restBus) {
        this.restBus = restBus;
        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(MOVIE_DB_HOST)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();

        api = adapter.create(MovieDatabaseAPI.class);
    }

    @Override
    public void getMovies() {
        api.getPopularMovies(API_KEY, new Callback<PopularMoviesApiResponse>() {
            @Override
            public void success(PopularMoviesApiResponse popularMoviesApiResponse, Response response) {
                Movie.List movies = popularMoviesApiResponse.getResults();
                restBus.post(movies);
            }

            @Override
            public void failure(RetrofitError error) {
                System.out.println("[ERROR] " + error.getMessage());
            }
        });
    }
}
