package com.github.mbarrben.moviedb.di;

import com.github.mbarrben.moviedb.domain.movies.GetMovies;
import com.github.mbarrben.moviedb.domain.movies.GetMoviesImpl;
import com.github.mbarrben.moviedb.model.MovieRepository;
import dagger.Module;
import dagger.Provides;
import rx.Scheduler;

@Module(
    includes = {
        CommonModule.class, ModelModule.class
    },
    library = true)
public final class DomainModule {

  @Provides GetMovies provideGetMovies(
      MovieRepository movieRepository,
      @IOScheduler Scheduler subscribeScheduler,
      @UIScheduler Scheduler observeScheduler
  ) {
    return new GetMoviesImpl(movieRepository, subscribeScheduler, observeScheduler);
  }
}
