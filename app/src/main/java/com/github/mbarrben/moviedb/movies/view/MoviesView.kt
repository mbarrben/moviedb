package com.github.mbarrben.moviedb.movies.view

import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.mbarrben.moviedb.R
import com.github.mbarrben.moviedb.commons.observe
import com.github.mbarrben.moviedb.databinding.MoviesFragmentBinding
import com.github.mbarrben.moviedb.movies.viewmodel.MoviesViewModel
import com.github.mbarrben.moviedb.movies.viewmodel.MoviesViewModel.Status.Success
import kotlinx.coroutines.ExperimentalCoroutinesApi
import timber.log.Timber

@ExperimentalCoroutinesApi
class MoviesView(
    private val viewModelProvider: MoviesViewModel.Provider = MoviesViewModel.Provider(),
    private val moviesAdapter: MoviesAdapter = MoviesAdapter()
) {

    fun onCreate(fragment: Fragment, binding: MoviesFragmentBinding) {
        val viewModel = viewModelProvider.of(fragment)

        binding.lifecycleOwner = fragment
        binding.viewModel = viewModel

        binding.root.findViewById<RecyclerView>(R.id.movies_recycler).apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(fragment.context, 2)
            adapter = moviesAdapter
        }

        fragment.observe(viewModel.status) {status ->
            Timber.tag("status").d("Status = $status")
            when (status) {
                is Success -> moviesAdapter.movies = status.movies
            }
        }
    }
}