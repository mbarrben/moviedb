package com.github.mbarrben.moviedb.movies.view

import com.github.mbarrben.moviedb.commons.BaseFragment
import com.github.mbarrben.moviedb.R
import com.github.mbarrben.moviedb.app.inject
import com.github.mbarrben.moviedb.databinding.MoviesFragmentBinding
import com.github.mbarrben.moviedb.movies.di.moviesModule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.android.synthetic.main.movies_content.view.movies_recycler as moviesRecycler

@ExperimentalCoroutinesApi
class MoviesFragment : BaseFragment<MoviesFragmentBinding>(
    layoutResId = R.layout.movies_fragment,
    modules = listOf(moviesModule)
) {

    private val view: MoviesView by inject()

    override fun onBind(binding: MoviesFragmentBinding) {
        view.onCreate(fragment = this, binding = binding)
    }
}
