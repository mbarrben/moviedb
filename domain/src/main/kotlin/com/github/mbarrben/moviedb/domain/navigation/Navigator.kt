package com.github.mbarrben.moviedb.domain.navigation

import com.github.mbarrben.moviedb.model.entities.Movie

interface Navigator {
  fun detail(movie: Movie)
  fun getMovie(): Movie
}