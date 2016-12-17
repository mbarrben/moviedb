package com.github.mbarrben.moviedb.movies.view.adapter

import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView.Adapter
import android.view.ViewGroup
import com.github.mbarrben.moviedb.R.layout
import com.github.mbarrben.moviedb.extensions.inflate
import com.github.mbarrben.moviedb.model.entities.Movie
import com.github.mbarrben.moviedb.model.entities.Movie.List
import com.github.mbarrben.moviedb.movies.view.MovieItemLayout
import com.github.mbarrben.moviedb.movies.view.adapter.Type.LOADING
import com.github.mbarrben.moviedb.movies.view.adapter.Type.MOVIE
import com.github.mbarrben.moviedb.movies.view.adapter.ViewHolder.MovieHolder
import com.github.mbarrben.moviedb.movies.view.adapter.ViewHolder.ProgressHolder
import kotlin.properties.Delegates

class MoviesAdapter : Adapter<ViewHolder>() {

  var movies: Movie.List by Delegates.observable(List(1, emptyList())) { _, old, new ->
    val diffResult = DiffUtil.calculateDiff(MoviesDiffCallback(old, new))
    diffResult.dispatchUpdatesTo(this)
  }

  var loading: Boolean by Delegates.observable(false) { _, old, _ ->
    if (old) notifyItemRemoved(movies.size) else notifyItemInserted(movies.size)
  }

  init {
    setHasStableIds(true)
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = when (Type.from(viewType)) {
    MOVIE   -> MovieHolder(parent.inflate(layout.movies_item) as MovieItemLayout)
    LOADING -> ProgressHolder(parent.inflate(layout.movies_item_loading))
  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(movies, position)

  override fun getItemViewType(position: Int) = if (position < movies.size) MOVIE.value else LOADING.value

  override fun getItemCount() = movies.size + if (loading) 1 else 0

  override fun getItemId(position: Int) = if (position < movies.size) movies[position].id else -1
}
