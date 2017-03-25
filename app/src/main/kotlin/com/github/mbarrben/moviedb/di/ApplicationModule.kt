package com.github.mbarrben.moviedb.di

import android.content.Context
import com.github.mbarrben.moviedb.BuildConfig
import com.github.mbarrben.moviedb.extensions.Timber
import com.github.mbarrben.moviedb.extensions.d
import com.github.mbarrben.moviedb.model.MovieRepository
import com.github.mbarrben.moviedb.model.rest.RestMovieRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.byteunits.DecimalByteUnit.MEGABYTES
import com.jakewharton.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import java.io.File
import javax.inject.Named
import javax.inject.Singleton

@Module
class ApplicationModule(val context: Context) {
  @Provides fun applicationContext(): Context = context
  @Provides @Named("UI") fun uiScheduler(): Scheduler = AndroidSchedulers.mainThread()
  @Provides @Named("IO") fun iOScheduler(): Scheduler = Schedulers.io()
  @Provides @Singleton fun okHttpClient(context: Context): OkHttpClient = createOkHttpClient(context).build()

  @Provides @Singleton fun picasso(context: Context, client: OkHttpClient): Picasso = Picasso.Builder(context)
      .downloader(OkHttp3Downloader(client))
      .listener { _, uri, e -> Timber.e(e) { "Failed to load image: $uri" } }
      .build()

  @Provides @Singleton fun gson(): Gson = GsonBuilder().setDateFormat("yyyy-MM-dd").create()

  @Provides fun movieRepository(
      client: OkHttpClient,
      gson: Gson
  ): MovieRepository = RestMovieRepository(client, BuildConfig.API_KEY, gson)

  private fun createOkHttpClient(context: Context): OkHttpClient.Builder {
    val logger = HttpLoggingInterceptor.Logger({ message -> Timber.tag("Retrofit").d { message } })
    val cacheDir = File(context.cacheDir, "http")
    val cache = Cache(cacheDir, MEGABYTES.toBytes(50))
    return OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor(logger).setLevel(BODY))
        .cache(cache)
  }
}