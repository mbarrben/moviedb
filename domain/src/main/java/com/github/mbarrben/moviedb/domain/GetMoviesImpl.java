package com.github.mbarrben.moviedb.domain;

import com.github.mbarrben.moviedb.model.MovieRepository;
import com.github.mbarrben.moviedb.model.entities.Movie;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

public class GetMoviesImpl implements GetMovies {

  private final MovieRepository repo;
  private final Bus uiBus;
  private final Bus modelBus;

  public GetMoviesImpl(MovieRepository repo, Bus modelBus, Bus uiBus) {
    this.repo = repo;
    this.uiBus = uiBus;
    this.modelBus = modelBus;
  }

  @Override
  public void get() {
    modelBus.register(this);
    repo.getMovies();
  }

  @Subscribe
  public void OnMoviesReceived(Movie.List movies) {
    uiBus.post(movies);
    modelBus.unregister(this);
  }
}
