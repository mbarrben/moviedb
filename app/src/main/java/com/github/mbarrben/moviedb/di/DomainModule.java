package com.github.mbarrben.moviedb.di;

import com.github.mbarrben.moviedb.domain.GetMovies;
import com.github.mbarrben.moviedb.domain.GetMoviesImpl;
import com.github.mbarrben.moviedb.model.MovieRepository;
import com.squareup.otto.Bus;
import dagger.Module;
import dagger.Provides;

@Module(
    includes = {
        CommonModule.class, ModelModule.class
    },
    library = true)
public final class DomainModule {

  @Provides GetMovies provideGetMovies(MovieRepository movieRepository, @UIBus Bus uiBus, @RestBus Bus modelBus) {
    return new GetMoviesImpl(movieRepository, modelBus, uiBus);
  }
}
