package com.github.mbarrben.moviedb.movies.ui.view

import androidx.fragment.app.Fragment
import com.github.mbarrben.moviedb.commons.extensions.observe
import com.github.mbarrben.moviedb.movies.ui.view.adapter.MoviesAdapter
import com.github.mbarrben.moviedb.movies.ui.viewmodel.MoviesViewModel
import com.github.mbarrben.moviedb.movies.ui.viewmodel.MoviesViewModel.Status.Success
import timber.log.Timber

internal class MoviesView(
    private val viewModelProvider: MoviesViewModel.Provider,
    private val moviesAdapter: MoviesAdapter
) {

    fun onCreate(fragment: Fragment, binding: MoviesFragmentBindingAdapter) {
        val viewModel = viewModelProvider.of(fragment)

        binding.lifecycleOwner = fragment
        binding.viewModel = viewModel

        binding.moviesRecycler.apply {
            setHasFixedSize(true)
            adapter = moviesAdapter
        }

        fragment.observe(viewModel.status) { status ->
            Timber.d("Status = $status")
            when (status) {
                is Success -> moviesAdapter.movies = status.movies
            }
        }

        viewModel.start()
    }
}