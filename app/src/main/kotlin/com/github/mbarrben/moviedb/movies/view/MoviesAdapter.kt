package com.github.mbarrben.moviedb.movies.view

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.Adapter
import android.view.ViewGroup
import com.github.mbarrben.moviedb.R.layout
import com.github.mbarrben.moviedb.extensions.inflate
import com.github.mbarrben.moviedb.model.entities.Movie
import com.github.mbarrben.moviedb.model.entities.Movie.List
import com.github.mbarrben.moviedb.movies.view.MovieItemLayout
import com.github.mbarrben.moviedb.movies.view.MoviesAdapter.ViewHolder

class MoviesAdapter(val list: List) : Adapter<ViewHolder>() {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(parent.inflate(layout.movies_item) as MovieItemLayout)

  override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(list[position])

  override fun getItemCount() = list.size

  class ViewHolder(val view: MovieItemLayout) : RecyclerView.ViewHolder(view) {
    fun bind(movie: Movie) = view.render(movie)
  }
}
