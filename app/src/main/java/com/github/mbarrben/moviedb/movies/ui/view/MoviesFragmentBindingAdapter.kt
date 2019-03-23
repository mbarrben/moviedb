package com.github.mbarrben.moviedb.movies.ui.view

import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.github.mbarrben.moviedb.databinding.MoviesFragmentBinding
import com.github.mbarrben.moviedb.movies.ui.viewmodel.MoviesViewModel

interface MoviesFragmentBindingAdapter {
    var lifecycleOwner: LifecycleOwner?
    var viewModel: MoviesViewModel?
    val moviesRecycler: RecyclerView
}

fun MoviesFragmentBinding.toAdapter() = object : MoviesFragmentBindingAdapter {
    override var lifecycleOwner: LifecycleOwner?
        get() = this@toAdapter.lifecycleOwner
        set(value) {
            this@toAdapter.lifecycleOwner = value
        }
    override var viewModel: MoviesViewModel?
        get() = this@toAdapter.viewModel
        set(value) {
            this@toAdapter.viewModel = value
        }
    override val moviesRecycler: RecyclerView = this@toAdapter.moviesContent.moviesRecycler
}