package com.github.mbarrben.moviedb.domain.moviesDetail

import com.github.mbarrben.moviedb.model.entities.Movie

interface DetailView {
  fun render(movie: Movie)
}