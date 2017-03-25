package com.github.mbarrben.moviedb.domain.moviesDetail

import com.github.mbarrben.moviedb.model.entities.Movie
import io.reactivex.Observable

interface DetailView {
  fun render(movie: Movie)
  fun details(details: Movie.Details)
  fun unbind()

  fun loaded(): Observable<Unit>
}