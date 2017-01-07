package com.github.mbarrben.moviedb.di

import android.content.Context
import com.github.mbarrben.moviedb.BaseActivity
import com.github.mbarrben.moviedb.MovieDbApp
import com.github.mbarrben.moviedb.model.MovieRepository
import com.github.mbarrben.moviedb.movieDetail.view.platform.DetailActivity
import com.github.mbarrben.moviedb.movies.view.platform.MoviesActivity
import com.google.gson.Gson
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
  fun inject(activity: BaseActivity)
  fun context(): Context
  @Named("UI") fun uiScheduler(): Scheduler
  @Named("IO") fun iOScheduler(): Scheduler
  fun okHttpClient(): OkHttpClient
  fun picasso(): Picasso
  fun gson(): Gson
  fun movieRepository(): MovieRepository
}