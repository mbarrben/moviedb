package com.github.mbarrben.moviedb.movielist;

import com.github.mbarrben.moviedb.model.entities.Movie;

public interface MoviesView {
  void showMovies(Movie.List movies);
}
