package com.github.mbarrben.moviedb.domain.moviesDetail

import com.github.mbarrben.moviedb.model.MovieRepository
import com.github.mbarrben.moviedb.model.entities.Movie
import io.reactivex.Observable
import io.reactivex.Scheduler

class GetDetails(val repo: MovieRepository, val subscribeScheduler: Scheduler, val observeScheduler: Scheduler) {

  fun get(id: Long): Observable<Movie.Details> = repo.details(id)
      .subscribeOn(subscribeScheduler)
      .observeOn(observeScheduler)
}