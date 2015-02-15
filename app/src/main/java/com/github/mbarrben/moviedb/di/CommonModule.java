package com.github.mbarrben.moviedb.di;

import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(library = true)
public final class CommonModule {

    @UIBus
    @Provides
    @Singleton
    Bus provideUiBus() {
        return new Bus();
    }

    @RestBus
    @Provides
    @Singleton
    Bus provideRestBus() {
        return new Bus(ThreadEnforcer.ANY);
    }
}
