package com.github.mbarrben.moviedb.di

import com.github.mbarrben.moviedb.BuildConfig

object NetworkModule {
    const val API_KEY = BuildConfig.API_KEY
    const val BASE_URL = "https://api.themoviedb.org/3/"
    const val POSTER_PREFIX = "https://image.tmdb.org/t/p/w500"
}