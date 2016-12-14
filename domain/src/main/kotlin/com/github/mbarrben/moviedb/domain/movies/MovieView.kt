package com.github.mbarrben.moviedb.domain.movies

import com.github.mbarrben.moviedb.model.entities.Movie

interface MovieView {
  fun render(movie: Movie)
}
