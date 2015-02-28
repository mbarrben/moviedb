package com.github.mbarrben.moviedb.di;

import com.github.mbarrben.moviedb.BuildConfig;
import com.github.mbarrben.moviedb.model.MovieRepository;
import com.github.mbarrben.moviedb.model.rest.RestMovieRepository;
import dagger.Module;
import dagger.Provides;

@Module(
    includes = {
        CommonModule.class
    },
    library = true)
public final class ModelModule {

  @Provides MovieRepository provideGetMovies() {
    return new RestMovieRepository(BuildConfig.DEBUG, BuildConfig.API_KEY);
  }
}
