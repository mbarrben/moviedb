package com.github.mbarrben.moviedb.movies.ui.view.adapter

import androidx.recyclerview.widget.RecyclerView
import com.github.mbarrben.moviedb.databinding.MoviesItemBinding
import com.github.mbarrben.moviedb.movies.ui.viewmodel.MovieViewModel

class MovieViewHolder(
    private val binding: MoviesItemBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(movie: MovieViewModel) {
        binding.viewModel = movie
    }
}