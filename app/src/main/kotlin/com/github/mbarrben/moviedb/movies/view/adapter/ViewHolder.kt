package com.github.mbarrben.moviedb.movies.view.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import com.github.mbarrben.moviedb.model.entities.Movie
import com.github.mbarrben.moviedb.model.entities.Movie.List
import com.github.mbarrben.moviedb.movies.view.MovieItemLayout
import io.reactivex.Observer

abstract class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
  abstract fun bind(movies: List, position: Int)

  class MovieHolder(val movieView: MovieItemLayout, movieClickSubscriber: Observer<Movie>) : ViewHolder(movieView) {

    init {
      movieView.movieClicks().subscribe(movieClickSubscriber)
    }

    override fun bind(movies: List, position: Int) {
      movieView.render(movies[position])
    }
  }

  class ProgressHolder(view: View) : ViewHolder(view) {
    override fun bind(movies: List, position: Int) = Unit
  }
}