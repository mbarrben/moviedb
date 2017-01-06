package com.github.mbarrben.moviedb.domain.movies

import com.github.mbarrben.moviedb.model.entities.Movie
import rx.Observable

interface MovieView {
  fun render(movie: Movie)

  fun movieClicks(): Observable<Movie>
}
