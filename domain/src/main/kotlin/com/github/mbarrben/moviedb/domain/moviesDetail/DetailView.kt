package com.github.mbarrben.moviedb.domain.moviesDetail

import com.github.mbarrben.moviedb.model.entities.Movie
import rx.Observable

interface DetailView {
  fun render(movie: Movie)
  fun details(details: Movie.Details)

  fun loaded(): Observable<Unit>
}