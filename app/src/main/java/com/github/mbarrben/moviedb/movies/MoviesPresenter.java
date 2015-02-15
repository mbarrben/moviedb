package com.github.mbarrben.moviedb.movies;

public interface MoviesPresenter {

  void onViewCreated(MoviesView view);

  void onDestroyView();
}
