package com.github.mbarrben.moviedb.domain;

import com.github.mbarrben.moviedb.model.MovieRepository;
import com.github.mbarrben.moviedb.model.entities.Movie;
import rx.Observable;
import rx.Scheduler;

public class GetMoviesImpl implements GetMovies {

  private final MovieRepository repo;
  private final Scheduler subscribeScheduler;
  private final Scheduler observeScheduler;

  public GetMoviesImpl(MovieRepository repo, Scheduler subscribeScheduler, Scheduler observeScheduler) {
    this.repo = repo;
    this.subscribeScheduler = subscribeScheduler;
    this.observeScheduler = observeScheduler;
  }

  @Override
  public Observable<Movie.List> get() {
    return repo.getMovies()
        .subscribeOn(subscribeScheduler)
        .observeOn(observeScheduler);
  }
}
