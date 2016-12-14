package com.github.mbarrben.moviedb.domain.movies

import com.github.mbarrben.moviedb.model.entities.Movie

interface MoviesView {
  fun showMovies(movies: Movie.List)
  fun showError()
}
