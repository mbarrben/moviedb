package com.github.mbarrben.moviedb

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import kotlin.coroutines.CoroutineContext

@ExperimentalCoroutinesApi
class MoviesFragment : Fragment(), CoroutineScope by MainScope() {

    private val getPopularMovies: GetPopularMovies = GetPopularMovies()
    private val context: CoroutineContext = Dispatchers.Default

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_movies, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        launch {
            val movies = withContext(context) { getPopularMovies() }
            Timber.d("Result: $movies")
        }
    }
}
