package com.github.mbarrben.moviedb.movies;

import android.support.annotation.NonNull;

public interface MoviesPresenter {

  void onViewCreated(@NonNull MoviesView view);

  void onDestroyView();
}
