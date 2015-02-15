package com.github.mbarrben.moviedb.di;

import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

@Module(library = true)
public final class CommonModule {

  @UIBus @Provides @Singleton Bus provideUiBus() {
    return new Bus();
  }

  @RestBus @Provides @Singleton Bus provideRestBus() {
    return new Bus(ThreadEnforcer.ANY);
  }
}
