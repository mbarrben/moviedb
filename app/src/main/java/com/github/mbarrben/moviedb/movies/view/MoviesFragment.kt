package com.github.mbarrben.moviedb.movies.view

import com.github.mbarrben.moviedb.R
import com.github.mbarrben.moviedb.databinding.MoviesFragmentBinding
import com.github.mbarrben.moviedb.BaseFragment
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.android.synthetic.main.movies_content.view.movies_recycler as moviesRecycler

@ExperimentalCoroutinesApi
class MoviesFragment : BaseFragment<MoviesFragmentBinding>(R.layout.movies_fragment) {

    private val view: MoviesView = MoviesView()

    override fun onBind(binding: MoviesFragmentBinding) {
        view.onCreate(fragment = this, binding = binding)
    }
}
