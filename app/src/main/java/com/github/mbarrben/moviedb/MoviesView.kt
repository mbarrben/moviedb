package com.github.mbarrben.moviedb

import androidx.fragment.app.Fragment
import com.github.mbarrben.moviedb.commons.observe
import kotlinx.coroutines.ExperimentalCoroutinesApi
import timber.log.Timber

@ExperimentalCoroutinesApi
class MoviesView(
    private val viewModelProvider: MoviesViewModel.Provider = MoviesViewModel.Provider()
) {

    fun onCreate(fragment: Fragment) {
        val viewModel = viewModelProvider.of(fragment)
        fragment.observe(viewModel.status) {
            Timber.tag("status").d("Status = $it")
        }
    }
}