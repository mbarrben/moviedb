package com.github.mbarrben.moviedb.domain.movies;

import com.github.mbarrben.moviedb.model.entities.Movie;

public interface MoviesView {

  void showMovies(Movie.List movies);

  void showError();
}
