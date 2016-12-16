package com.github.mbarrben.moviedb.di

import android.content.Context
import com.github.mbarrben.moviedb.BaseActivity
import com.github.mbarrben.moviedb.MovieDbApp
import com.github.mbarrben.moviedb.movies.view.MoviesActivity
import com.squareup.picasso.Picasso
import dagger.Component
import okhttp3.OkHttpClient
import rx.Scheduler
import javax.inject.Named
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(ApplicationModule::class))
interface ApplicationComponent {
  fun inject(application: MovieDbApp)
  fun inject(activity: MoviesActivity)
  fun inject(activity: BaseActivity)
  fun context(): Context
  @Named("UI") fun provideUiScheduler(): Scheduler
  @Named("IO") fun provideIOScheduler(): Scheduler
  fun provideOkHttpClient(): OkHttpClient
  fun providePicasso(): Picasso
}