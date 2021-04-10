package com.github.mbarrben.moviedb.navigation.composables

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltNavGraphViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.github.mbarrben.moviedb.detail.DetailScreen
import com.github.mbarrben.moviedb.movies.domain.Movie
import com.github.mbarrben.moviedb.movies.ui.composables.MoviesScreen
import com.github.mbarrben.moviedb.navigation.Navigation.Detail
import com.github.mbarrben.moviedb.navigation.Navigation.Movies
import com.github.mbarrben.moviedb.navigation.NavigationManager

@Composable
fun Navigation(navigationManager: NavigationManager) {
    val navController = rememberNavController()

    val destination = navigationManager.commands.value.destination
    if (destination.isNotEmpty()) {
        navController.navigate(destination)
    }

    NavHost(navController, startDestination = Movies.destination) {
        composable(Movies.route) { MoviesScreen(hiltNavGraphViewModel()) }
        composable(
            route = Detail.route,
            arguments = Detail.arguments,
        ) { backStackEntry ->
            val movieTitle = backStackEntry.arguments?.getString(Detail.KEY_TITLE)!!
            DetailScreen(
                Movie(
                    id = 0,
                    title = movieTitle,
                    originalTitle = "",
                    overview = "",
                    releaseDate = null,
                    originalLanguage = "",
                    voteCount = 0,
                    voteAverage = 0F,
                    posterPath = null,
                    backdropPath = null
                )
            )
        }
    }
}