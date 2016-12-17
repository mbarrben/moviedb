package com.github.mbarrben.moviedb.movies.view.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import com.github.mbarrben.moviedb.model.entities.Movie.List
import com.github.mbarrben.moviedb.movies.view.MovieItemLayout

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