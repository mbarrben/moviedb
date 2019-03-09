package com.github.mbarrben.moviedb.movies.ui.view.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.github.mbarrben.moviedb.R
import com.github.mbarrben.moviedb.commons.extensions.inflateBinding
import com.github.mbarrben.moviedb.databinding.MoviesItemBinding
import com.github.mbarrben.moviedb.movies.ui.viewmodel.MovieViewModel
import kotlin.properties.Delegates

class MoviesAdapter : RecyclerView.Adapter<MovieViewHolder>() {

    var movies: List<MovieViewModel> by Delegates.observable(emptyList()) { _, old, new ->
        DiffUtil.calculateDiff(MoviesDiffCallback(old, new))
            .dispatchUpdatesTo(this)
    }

    init {
        setHasStableIds(true)
    }

    override fun getItemCount(): Int = movies.size

    override fun getItemId(position: Int): Long = movies[position].id

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = parent.inflateBinding<MoviesItemBinding>(R.layout.movies_item, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies[position])
    }
}

