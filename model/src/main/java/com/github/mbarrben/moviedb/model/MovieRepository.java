package com.github.mbarrben.moviedb.model;

import com.github.mbarrben.moviedb.model.entities.Movie;
import rx.Observable;

public interface MovieRepository {
  Observable<Movie.List> getMovies();
}
