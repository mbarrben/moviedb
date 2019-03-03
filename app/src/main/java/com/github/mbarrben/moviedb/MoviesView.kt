package com.github.mbarrben.moviedb

import androidx.fragment.app.Fragment
import com.github.mbarrben.moviedb.commons.observe
import com.github.mbarrben.moviedb.databinding.FragmentMoviesBinding
import com.github.mbarrben.moviedb.viewmodel.MoviesViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import timber.log.Timber

@ExperimentalCoroutinesApi
class MoviesView(
    private val viewModelProvider: MoviesViewModel.Provider = MoviesViewModel.Provider()
) {

    fun onCreate(fragment: Fragment, binding: FragmentMoviesBinding) {
        val viewModel = viewModelProvider.of(fragment)

        binding.lifecycleOwner = fragment
        binding.viewModel = viewModel

        fragment.observe(viewModel.status) {
            Timber.tag("status").d("Status = $it")
        }
    }
}