package com.github.mbarrben.moviedb.movies.view

import android.support.v7.widget.RecyclerView
import android.view.View
import com.github.mbarrben.moviedb.model.entities.Movie.List

abstract class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
  abstract fun bind(movies: List, position: Int)

  class MovieHolder(val movieView: MovieItemLayout) : ViewHolder(movieView) {
    override fun bind(movies: List, position: Int) {
      movieView.render(movies[position])
    }
  }

  class ProgressHolder(view: View) : ViewHolder(view) {
    override fun bind(movies: List, position: Int) = Unit
  }
}