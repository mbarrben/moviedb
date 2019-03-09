package com.github.mbarrben.moviedb.app.di

import android.content.Context
import com.github.mbarrben.moviedb.movies.data.network.DateAdapter
import com.github.mbarrben.moviedb.movies.data.network.DefaultHeadersInterceptor
import com.github.mbarrben.moviedb.movies.data.network.ImageUrlAdapter
import com.github.mbarrben.moviedb.movies.data.network.MoviesDatabaseService
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

fun createHttpModule(context: Context) = createModule("httpModule") {

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
        val dateAdapter = DateAdapter("yyyy-MM-dd")
        val imageUrlAdapter = ImageUrlAdapter(MoviesDatabaseService.POSTER_PREFIX)

        Moshi.Builder()
            .add(dateAdapter)
            .add(imageUrlAdapter)
            .build()
    }

    factory {
        val okHttpClient: OkHttpClient = get()
        val moshi: Moshi = get()

        val moshiConverterFactory = MoshiConverterFactory.create(moshi)

        Retrofit.Builder()
            .baseUrl(MoviesDatabaseService.BASE_URL)
            .addConverterFactory(moshiConverterFactory)
            .client(okHttpClient)
            .build()
            .create(MoviesDatabaseService::class.java)
    }
}