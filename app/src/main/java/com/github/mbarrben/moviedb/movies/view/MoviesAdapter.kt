package com.github.mbarrben.moviedb.movies.view

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.github.mbarrben.moviedb.R
import com.github.mbarrben.moviedb.commons.inflateBinding
import com.github.mbarrben.moviedb.databinding.MoviesItemBinding
import com.github.mbarrben.moviedb.movies.data.network.model.Dto
import kotlin.properties.Delegates

class MoviesAdapter : RecyclerView.Adapter<MovieViewHolder>() {

    var movies: List<Dto.Movie> by Delegates.observable(emptyList()) { _, old, new ->
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

class MovieViewHolder(
    private val binding: MoviesItemBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(movie: Dto.Movie) {
        binding.viewModel = movie
    }
}

class MoviesDiffCallback(
    private val old: List<Dto.Movie>,
    private val new: List<Dto.Movie>
) : DiffUtil.Callback() {
    override fun areItemsTheSame(oldPosition: Int, newPosition: Int) = old[oldPosition].id == new[newPosition].id

    override fun getOldListSize() = old.size

    override fun getNewListSize() = new.size

    override fun areContentsTheSame(oldPosition: Int, newPosition: Int) = old[oldPosition] == new[newPosition]
}