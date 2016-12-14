package com.github.mbarrben.moviedb.domain.movies

import com.github.mbarrben.moviedb.model.MovieRepository
import com.github.mbarrben.moviedb.model.entities.Movie
import rx.Observable
import rx.Scheduler

class GetMovies(val repo: MovieRepository, val subscribeScheduler: Scheduler, val observeScheduler: Scheduler) {

  fun get(): Observable<Movie.List> = repo.getMovies()
      .subscribeOn(subscribeScheduler)
      .observeOn(observeScheduler)
}
