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

  init {
    inflate(R.layout.movies_view, attachToRoot = true)
  }

  override fun showMovies(movies: List) {
    moviesRecycler.adapter = MoviesAdapter(movies)
  }

  override fun showError() = Unit
}
