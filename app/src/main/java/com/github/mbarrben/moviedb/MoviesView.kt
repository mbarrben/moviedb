package com.github.mbarrben.moviedb

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import kotlin.coroutines.CoroutineContext

@ExperimentalCoroutinesApi
class MoviesView(
    private val getPopularMovies: GetPopularMovies = GetPopularMovies(),
    private val context: CoroutineContext = Dispatchers.Default
): CoroutineScope by MainScope() {

    fun onCreate() {
        launch {
            val movies = withContext(context) { getPopularMovies() }
            Timber.d("Result: $movies")
        }
    }
}