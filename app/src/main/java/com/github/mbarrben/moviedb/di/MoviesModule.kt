package com.github.mbarrben.moviedb.di

import android.content.Context
import android.util.Log
import com.github.mbarrben.moviedb.commons.network.DateAdapter
import com.github.mbarrben.moviedb.commons.network.DefaultHeadersInterceptor
import com.github.mbarrben.moviedb.commons.network.ImageUrlAdapter
import com.github.mbarrben.moviedb.movies.data.MoviesRepository
import com.github.mbarrben.moviedb.movies.data.network.MoviesApiClient
import com.github.mbarrben.moviedb.movies.data.network.MoviesService
import com.jakewharton.byteunits.DecimalByteUnit
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
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
import java.io.File

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {
    @Provides
    fun provideMoviesRepository(apiClient: MoviesApiClient): MoviesRepository = MoviesRepository(
        apiClient = apiClient,
        apiKey = NetworkModule.API_KEY
    )

    @Provides
    fun provideMoviesService(@ApplicationContext context: Context): MoviesService {
        val dateAdapter = DateAdapter("yyyy-MM-dd")
        val imageUrlAdapter = ImageUrlAdapter(NetworkModule.POSTER_PREFIX)

        val moshi = Moshi.Builder()
            .add(dateAdapter)
            .add(imageUrlAdapter)
            .build()

        val cache = Cache(
            File(context.cacheDir, "http"),
            DecimalByteUnit.MEGABYTES.toBytes(50)
        )

        val logger = object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                Log.d("Retrofit", message)
            }
        }
        val loggingInterceptor = HttpLoggingInterceptor(logger).setLevel(HttpLoggingInterceptor.Level.BODY)

        val okhttpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(DefaultHeadersInterceptor())
            .cache(cache)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(NetworkModule.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(okhttpClient)
            .build()

        return retrofit.create(MoviesService::class.java)
    }
}