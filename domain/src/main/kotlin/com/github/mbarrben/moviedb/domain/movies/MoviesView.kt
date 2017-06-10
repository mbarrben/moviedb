package com.github.mbarrben.moviedb.domain.movies

import com.github.mbarrben.moviedb.model.entities.Movie
import io.reactivex.Observable

interface MoviesView {
  fun showMovies(movies: Movie.List)
  fun addMovies(movies: Movie.List)
  fun showError()
  fun showLoading()
  fun hideLoading()
  fun showEmpty()

  fun paginationEvents(): Observable<Int>
  fun movieClicks(): Observable<Movie>
  fun searchQueries(): Observable<String>
  fun searchClosed(): Observable<Unit>
}
