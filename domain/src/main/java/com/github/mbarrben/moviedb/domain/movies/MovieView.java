package com.github.mbarrben.moviedb.domain.movies;

import com.github.mbarrben.moviedb.model.entities.Movie;

public interface MovieView {

  void render(Movie movie);
}
