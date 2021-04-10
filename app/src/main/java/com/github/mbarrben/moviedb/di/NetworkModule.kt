package com.github.mbarrben.moviedb.di

import android.content.Context
import coil.ImageLoader
import com.github.mbarrben.moviedb.BuildConfig
import com.github.mbarrben.moviedb.commons.network.DateAdapter
import com.github.mbarrben.moviedb.commons.network.DefaultHeadersInterceptor
import com.github.mbarrben.moviedb.commons.network.ImageUrlAdapter
import com.jakewharton.byteunits.DecimalByteUnit
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import timber.log.Timber
import java.io.File
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class Images

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class Api

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    const val API_KEY = BuildConfig.API_KEY

    private const val BASE_URL = "https://api.themoviedb.org/3/"
    private const val POSTER_PREFIX = "https://image.tmdb.org/t/p/w500"

    @Provides
    fun providesMoshi(): Moshi {
        val dateAdapter = DateAdapter("yyyy-MM-dd")
        val imageUrlAdapter = ImageUrlAdapter(POSTER_PREFIX)

        return Moshi.Builder()
            .add(dateAdapter)
            .add(imageUrlAdapter)
            .build()
    }

    @Provides
    fun provideRetrofit(
        @Api okHttpClient: OkHttpClient,
        moshi: Moshi,
    ): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .client(okHttpClient)
        .build()

    @Singleton
    @Provides
    fun provideImageLoader(
        @ApplicationContext context: Context,
        @Images okHttpClient: OkHttpClient,
    ): ImageLoader = ImageLoader.Builder(context)
        .crossfade(true)
        .okHttpClient(okHttpClient)
        .build()

    @Api
    @Provides
    fun provideOkHttpClientForApi(@ApplicationContext context: Context): OkHttpClient {
        val cache = Cache(
            File(context.cacheDir, "http"),
            DecimalByteUnit.MEGABYTES.toBytes(50)
        )

        val logger = object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                Timber.tag("Api").d(message)
            }
        }
        val loggingInterceptor = HttpLoggingInterceptor(logger).setLevel(HttpLoggingInterceptor.Level.BODY)

        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(DefaultHeadersInterceptor())
            .cache(cache)
            .build()
    }

    @Images
    @Provides
    fun provideOkHttpClientForImageLoading(@ApplicationContext context: Context): OkHttpClient {
        val cache = Cache(
            File(context.cacheDir, "images"),
            DecimalByteUnit.MEGABYTES.toBytes(250)
        )

        val logger = object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                Timber.tag("ImageLoading").d(message)
            }
        }
        val loggingInterceptor = HttpLoggingInterceptor(logger).setLevel(HttpLoggingInterceptor.Level.BASIC)

        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .cache(cache)
            .build()
    }
}