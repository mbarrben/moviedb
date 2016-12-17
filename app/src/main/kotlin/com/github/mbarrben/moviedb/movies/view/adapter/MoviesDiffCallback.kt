package com.github.mbarrben.moviedb.movies.view.adapter

import android.support.v7.util.DiffUtil
import com.github.mbarrben.moviedb.model.entities.Movie

class MoviesDiffCallback(val old: Movie.List, val new: Movie.List) : DiffUtil.Callback() {
  override fun areItemsTheSame(oldPosition: Int, newPosition: Int) = old[oldPosition].id == new[newPosition].id

  override fun getOldListSize() = old.size

  override fun getNewListSize() = new.size

  override fun areContentsTheSame(oldPosition: Int, newPosition: Int) = old[oldPosition] == new[newPosition]
}