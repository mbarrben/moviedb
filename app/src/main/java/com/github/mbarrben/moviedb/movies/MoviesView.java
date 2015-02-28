package com.github.mbarrben.moviedb.movies;

import com.github.mbarrben.moviedb.model.entities.Movie;

public interface MoviesView {
  void showMovies(Movie.List movies);

  void showError();
}
