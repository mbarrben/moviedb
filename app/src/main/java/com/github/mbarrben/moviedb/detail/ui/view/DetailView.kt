package com.github.mbarrben.moviedb.detail.ui.view

import androidx.fragment.app.Fragment
import com.github.mbarrben.moviedb.databinding.DetailFragmentBinding
import com.github.mbarrben.moviedb.detail.ui.viewmodel.DetailViewModel
import com.github.mbarrben.moviedb.movies.domain.Movie

internal class DetailView(
    private val viewModelProvider: DetailViewModel.Provider
) {

    fun onCreate(fragment: Fragment, binding: DetailFragmentBinding, movie: Movie) {
        binding.lifecycleOwner = fragment
        binding.viewModel = viewModelProvider.of(fragment, movie)
    }
}