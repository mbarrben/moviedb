package com.github.mbarrben.moviedb.domain.movies;

import com.github.mbarrben.moviedb.model.entities.Movie;
import rx.Subscriber;
import rx.Subscription;

public class MoviesPresenterImpl implements MoviesPresenter {

  private final GetMovies getMovies;

  private MoviesView view;
  private Subscription subscription;

  public MoviesPresenterImpl(GetMovies getMovies) {
    this.getMovies = getMovies;
  }

  @Override
  public void onViewCreated(MoviesView view) {
    this.view = view;
    checkView();
    subscription = getMovies.get()
        .subscribe(new Subscriber<Movie.List>() {
          @Override
          public void onCompleted() {

          }

          @Override
          public void onError(Throwable e) {
            view.showError();
          }

          @Override
          public void onNext(Movie.List movies) {
            view.showMovies(movies);
          }
        });
  }

  @Override
  public void onDestroyView() {
    subscription.unsubscribe();
  }

  private void checkView() {
    if (view == null) {
      throw new IllegalStateException("Set a view before doing anything else in this presenter");
    }
  }
}
