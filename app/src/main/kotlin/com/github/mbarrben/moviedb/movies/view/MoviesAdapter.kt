package com.github.mbarrben.moviedb.movies.view

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.Adapter
import android.view.ViewGroup
import com.github.mbarrben.moviedb.R
import com.github.mbarrben.moviedb.extensions.inflate
import com.github.mbarrben.moviedb.model.entities.Movie
import com.github.mbarrben.moviedb.movies.view.MoviesAdapter.ViewHolder
import kotlin.properties.Delegates

class MoviesAdapter : Adapter<ViewHolder>() {

  var movies: Movie.List by Delegates.observable(Movie.List()) { _, _, _ -> notifyDataSetChanged() }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(parent.inflate(R.layout.movies_item) as MovieItemLayout)

  override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(movies[position])

  override fun getItemCount() = movies.size

  class ViewHolder(val view: MovieItemLayout) : RecyclerView.ViewHolder(view) {
    fun bind(movie: Movie) = view.render(movie)
  }
}
