package com.github.mbarrben.moviedb.navigation.composables

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
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
    val arguments = navigationManager.commands.value.bundle
    if (destination.isNotEmpty()) {
        navController.currentBackStackEntry?.arguments?.putAll(arguments)
        navController.navigate(destination)
    }

    NavHost(navController, startDestination = Movies.destination) {
        composable(Movies.route) { MoviesScreen(hiltViewModel()) }
        composable(
            route = Detail.route,
            arguments = Detail.arguments,
        ) {
            val movie = navController.previousBackStackEntry?.arguments?.getParcelable<Movie>(Detail.KEY_MOVIE)!!
            DetailScreen(movie)
        }
    }
}