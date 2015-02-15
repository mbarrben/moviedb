package com.github.mbarrben.moviedb.di;

import com.github.mbarrben.moviedb.model.MovieRepository;
import com.github.mbarrben.moviedb.model.rest.RestMovieRepository;
import com.squareup.otto.Bus;

import dagger.Module;
import dagger.Provides;

@Module(
        includes = {
                CommonModule.class
        },
        library = true
)
public final class ModelModule {

    @Provides
    MovieRepository provideGetMovies(@RestBus Bus restBus) {
        return new RestMovieRepository(restBus);
    }
}
