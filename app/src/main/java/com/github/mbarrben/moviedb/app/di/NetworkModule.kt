package com.github.mbarrben.moviedb.app.di

import android.content.Context
import com.github.mbarrben.moviedb.BuildConfig
import com.github.mbarrben.moviedb.app.di.NetworkModule.API_KEY
import com.github.mbarrben.moviedb.app.di.NetworkModule.BASE_URL
import com.github.mbarrben.moviedb.app.di.NetworkModule.POSTER_PREFIX
import com.github.mbarrben.moviedb.commons.network.DateAdapter
import com.github.mbarrben.moviedb.commons.network.DefaultHeadersInterceptor
import com.github.mbarrben.moviedb.commons.network.ImageUrlAdapter
import com.jakewharton.byteunits.DecimalByteUnit.MEGABYTES
import com.squareup.moshi.Moshi
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.rewedigital.katana.createModule
import org.rewedigital.katana.dsl.compact.factory
import org.rewedigital.katana.dsl.compact.singleton
import org.rewedigital.katana.dsl.get
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import timber.log.Timber
import java.io.File

object NetworkModule {
    const val API_KEY = "API_KEY"
    const val BASE_URL = "BASE_URL"
    const val POSTER_PREFIX = "POSTER_PREFIX"
}

fun createNetworkModule(context: Context) = createModule("networkModule") {

    factory(API_KEY) { BuildConfig.API_KEY }
    factory(BASE_URL) { "https://api.themoviedb.org/3/" }
    factory(POSTER_PREFIX) { "https://image.tmdb.org/t/p/w500" }

    factory {
        Cache(
            File(context.cacheDir, "http"),
            MEGABYTES.toBytes(50)
        )
    }

    singleton {
        val logger = HttpLoggingInterceptor.Logger { message -> Timber.tag("Retrofit").d(message) }
        val loggingInterceptor = HttpLoggingInterceptor(logger).setLevel(HttpLoggingInterceptor.Level.BODY)
        val cache: Cache = get()

        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(DefaultHeadersInterceptor())
            .cache(cache)
            .build()
    }

    factory {
        val posterPrefix: String = get(POSTER_PREFIX)

        val dateAdapter = DateAdapter("yyyy-MM-dd")
        val imageUrlAdapter = ImageUrlAdapter(posterPrefix)

        Moshi.Builder()
            .add(dateAdapter)
            .add(imageUrlAdapter)
            .build()
    }

    factory {
        val baseUrl: String = get(BASE_URL)
        val okHttpClient: OkHttpClient = get()
        val moshi: Moshi = get()

        val moshiConverterFactory = MoshiConverterFactory.create(moshi)

        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(moshiConverterFactory)
            .client(okHttpClient)
            .build()
    }
}