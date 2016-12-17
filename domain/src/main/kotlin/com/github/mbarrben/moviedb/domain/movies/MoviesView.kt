package com.github.mbarrben.moviedb.domain.movies

import com.github.mbarrben.moviedb.model.entities.Movie
import rx.Observable

interface MoviesView {
  fun showMovies(movies: Movie.List)
  fun showError()
  fun showLoading()
  fun hideLoading()
  fun paginationEvents(): Observable<Int>
}
