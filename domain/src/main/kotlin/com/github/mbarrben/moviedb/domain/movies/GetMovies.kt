package com.github.mbarrben.moviedb.domain.movies

import com.github.mbarrben.moviedb.model.MovieRepository
import com.github.mbarrben.moviedb.model.entities.Movie.List
import io.reactivex.Observable
import io.reactivex.Scheduler

class GetMovies(val repo: MovieRepository, val subscribeScheduler: Scheduler, val observeScheduler: Scheduler) {

  fun get(page: Int = 1): Observable<List> = repo.popular(page)
      .subscribeOn(subscribeScheduler)
      .observeOn(observeScheduler)
}
