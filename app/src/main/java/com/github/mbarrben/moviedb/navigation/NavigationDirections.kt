package com.github.mbarrben.moviedb.navigation

import android.os.Bundle
import androidx.navigation.compose.NamedNavArgument
import com.github.mbarrben.moviedb.movies.domain.Movie

interface NavigationDirections {
    val route: String
    val arguments: List<NamedNavArgument>
}

sealed class Navigation(
    val destination: String,
) {
    abstract val bundle: Bundle

    object Default : Navigation(""), NavigationDirections {
        override val route = destination
        override val arguments: List<NamedNavArgument> = emptyList()
        override val bundle: Bundle = Bundle.EMPTY
    }

    object Movies : Navigation("movies"), NavigationDirections {
        override val route = destination
        override val arguments: List<NamedNavArgument> = emptyList()
        override val bundle: Bundle = Bundle.EMPTY
    }

    data class Detail(val movie: Movie) : Navigation("detail/${movie.id}") {
        override val bundle: Bundle = Bundle().apply {
            putParcelable(KEY_MOVIE, movie)
        }

        companion object : NavigationDirections {
            const val KEY_MOVIE = "movie"
            override val route = "detail/{$KEY_MOVIE}"

            // override val arguments = listOf(navArgument(KEY_MOVIE) { type = NavType.ParcelableType(Movie::class.java) })
            override val arguments: List<NamedNavArgument> = emptyList()
        }
    }
}