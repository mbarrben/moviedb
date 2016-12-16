package com.github.mbarrben.moviedb.di

import android.content.Context
import dagger.Module
import dagger.Provides
import rx.Scheduler
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import javax.inject.Named

@Module
class ApplicationModule(val context: Context) {
  @Provides fun provideApplicationContext() = context
  @Provides @Named("UI") fun provideUiScheduler(): Scheduler = AndroidSchedulers.mainThread()
  @Provides @Named("IO") fun provideIOScheduler(): Scheduler = Schedulers.io()
}