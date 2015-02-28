package com.github.mbarrben.moviedb.domain.movies;

public interface MoviesPresenter {

  void onViewCreated(MoviesView view);

  void onDestroyView();
}
