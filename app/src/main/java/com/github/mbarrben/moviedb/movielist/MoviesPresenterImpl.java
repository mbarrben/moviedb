package com.github.mbarrben.moviedb.movielist;

import android.util.Log;
import com.github.mbarrben.moviedb.domain.GetMovies;
import com.github.mbarrben.moviedb.model.entities.Movie;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;
import javax.inject.Inject;

public class MoviesPresenterImpl implements MoviesPresenter {

  private final GetMovies getMovies;
  private final Bus bus;

  @Inject
  public MoviesPresenterImpl(GetMovies getMovies, Bus bus) {
    this.getMovies = getMovies;
    this.bus = bus;
  }

  @Override public void onViewCreated() {
    bus.register(this);
    getMovies.get();
  }

  @Override public void onDestroyView() {
    bus.unregister(this);
  }

  @Subscribe
  public void onMoviesReceived(Movie.List movies) {
    Log.d("Movida", "onMoviesReceived");
  }
}
