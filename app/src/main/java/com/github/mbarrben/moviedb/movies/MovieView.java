package com.github.mbarrben.moviedb.movies;

import com.github.mbarrben.moviedb.model.entities.Movie;

public interface MovieView {
  void render(Movie movie);
}
