package com.github.mbarrben.moviedb.movies.view

import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout
import com.github.mbarrben.moviedb.R
import com.github.mbarrben.moviedb.domain.movies.MoviesView
import com.github.mbarrben.moviedb.extensions.inflate
import com.github.mbarrben.moviedb.model.entities.Movie.List
import kotlinx.android.synthetic.main.movies_view.view.moviesRecycler

class MoviesViewLayout(context: Context, attrs: AttributeSet) : RelativeLayout(context, attrs), MoviesView {

  private val adapter = MoviesAdapter()

  init {
    inflate(R.layout.movies_view, attachToRoot = true)
//    moviesRecycler.layoutManager = GridAutoFitLayoutManager(context, context.resources.getDimensionPixelOffset(R.dimen.card_width))
    moviesRecycler.setHasFixedSize(true)
    moviesRecycler.addItemDecoration(MovieItemDecoration(context))
    moviesRecycler.adapter = adapter
  }

  override fun showMovies(movies: List) {
    adapter.movies = movies
  }

  override fun showError() = Unit
}
