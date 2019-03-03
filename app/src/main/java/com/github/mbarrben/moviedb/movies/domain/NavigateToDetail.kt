package com.github.mbarrben.moviedb.movies.domain

import android.view.View
import androidx.navigation.findNavController
import com.github.mbarrben.moviedb.movies.view.MoviesFragmentDirections

class NavigateToDetail {

    operator fun invoke(view: View) {
        val action = MoviesFragmentDirections.actionMoviesToDetail()
        view.findNavController().navigate(action)
    }
}