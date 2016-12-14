package com.github.mbarrben.moviedb.di

import android.content.Context
import dagger.Module
import dagger.Provides
import rx.Scheduler
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

@Module
class ApplicationModule(val context: Context) {
  @Provides fun provideApplicationContext() = context
  @Provides @UIScheduler fun provideUiScheduler(): Scheduler = AndroidSchedulers.mainThread()
  @Provides @IOScheduler fun provideIOScheduler(): Scheduler = Schedulers.io()
}