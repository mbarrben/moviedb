package com.github.mbarrben.moviedb.movies.ui.view.adapter

import androidx.recyclerview.widget.DiffUtil
import com.github.mbarrben.moviedb.movies.ui.viewmodel.MovieViewModel

internal class MoviesDiffCallback(
    private val old: List<MovieViewModel>,
    private val new: List<MovieViewModel>
) : DiffUtil.Callback() {
    override fun areItemsTheSame(oldPosition: Int, newPosition: Int) = old[oldPosition].id == new[newPosition].id

    override fun getOldListSize() = old.size

    override fun getNewListSize() = new.size

    override fun areContentsTheSame(oldPosition: Int, newPosition: Int) = old[oldPosition] == new[newPosition]
}