package com.github.mbarrben.moviedb.movies;

import com.github.mbarrben.moviedb.di.DomainModule;
import com.github.mbarrben.moviedb.domain.movies.GetMovies;
import com.github.mbarrben.moviedb.domain.movies.MoviesPresenter;
import com.github.mbarrben.moviedb.domain.movies.MoviesPresenterImpl;
import dagger.Module;
import dagger.Provides;

@Module(
    complete = false,
    includes = {
        DomainModule.class
    },
    injects = {
        MoviesActivity.class, MoviesFragment.class
    })
public class MoviesListModule {

  @Provides MoviesPresenter provideMoviesPresenter(GetMovies getMovies) {
    return new MoviesPresenterImpl(getMovies);
  }
}
