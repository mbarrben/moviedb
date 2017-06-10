package com.github.mbarrben.moviedb.domain.movies

import com.github.mbarrben.moviedb.model.MovieRepository
import com.github.mbarrben.moviedb.model.entities.Movie
import io.reactivex.Observable
import io.reactivex.Scheduler

class SearchMovies(val repo: MovieRepository, val subscribeScheduler: Scheduler, val observeScheduler: Scheduler) {

  fun search(query: String, page: Int = 1): Observable<Movie.List> = when {
    query.isEmpty() -> emptyMovieList()
    else            -> repoSearch(query, page)
  }

  private fun emptyMovieList() = Observable.just(Movie.List.EMPTY)

  private fun repoSearch(query: String, page: Int = 1) = repo.search(query, page)
      .subscribeOn(subscribeScheduler)
      .observeOn(observeScheduler)
}
