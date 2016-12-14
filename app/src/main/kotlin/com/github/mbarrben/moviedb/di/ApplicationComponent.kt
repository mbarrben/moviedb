package com.github.mbarrben.moviedb.di

import android.content.Context
import com.github.mbarrben.moviedb.BaseActivity
import com.github.mbarrben.moviedb.MovieDbApp
import com.github.mbarrben.moviedb.movies.view.MoviesActivity
import dagger.Component
import rx.Scheduler
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(ApplicationModule::class))
interface ApplicationComponent {
  fun inject(application: MovieDbApp)
  fun inject(activity: MoviesActivity)
  fun inject(activity: BaseActivity)
  fun context(): Context
  @UIScheduler fun provideUiScheduler(): Scheduler
  @IOScheduler fun provideIOScheduler(): Scheduler
}