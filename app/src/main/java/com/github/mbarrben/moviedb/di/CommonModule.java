package com.github.mbarrben.moviedb.di;

import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

@Module(library = true)
public final class CommonModule {

  @UIScheduler @Provides @Singleton Scheduler provideUiScheduler() {
    return AndroidSchedulers.mainThread();
  }

  @IOScheduler @Provides @Singleton Scheduler provideIOScheduler() {
    return Schedulers.io();
  }
}
