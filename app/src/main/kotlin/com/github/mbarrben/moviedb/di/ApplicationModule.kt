package com.github.mbarrben.moviedb.di

import android.content.Context
import com.github.mbarrben.moviedb.extensions.Timber
import com.github.mbarrben.moviedb.extensions.d
import com.jakewharton.byteunits.DecimalByteUnit.MEGABYTES
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import rx.Scheduler
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.io.File
import javax.inject.Named
import javax.inject.Singleton

@Module
class ApplicationModule(val context: Context) {
  @Provides fun provideApplicationContext(): Context = context
  @Provides @Named("UI") fun provideUiScheduler(): Scheduler = AndroidSchedulers.mainThread()
  @Provides @Named("IO") fun provideIOScheduler(): Scheduler = Schedulers.io()
  @Provides @Singleton fun provideOkHttpClient(context: Context): OkHttpClient = createOkHttpClient(context).build()

  private fun createOkHttpClient(context: Context): OkHttpClient.Builder {
    val logger = HttpLoggingInterceptor.Logger({ message -> Timber.tag("Retrofit").d { message } })
    val cacheDir = File(context.cacheDir, "http")
    val cache = Cache(cacheDir, MEGABYTES.toBytes(50))
    return OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor(logger).setLevel(BODY))
        .cache(cache)
  }
}