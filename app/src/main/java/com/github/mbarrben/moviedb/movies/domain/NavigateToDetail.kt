package com.github.mbarrben.moviedb.movies.domain

import android.view.View
import androidx.navigation.findNavController
import com.github.mbarrben.moviedb.movies.ui.view.MoviesFragmentDirections

class NavigateToDetail {

    operator fun invoke(view: View, movie: Movie) {
        val action = MoviesFragmentDirections.actionMoviesToDetail(movie)
        view.findNavController().navigate(action)
    }
}