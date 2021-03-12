package com.github.mbarrben.moviedb.movies.ui.composables

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.github.mbarrben.moviedb.movies.ui.viewmodel.MoviesViewModel
import com.github.mbarrben.moviedb.movies.ui.viewmodel.MoviesViewModel.State.*

@Composable
fun MoviesScreen(state: MoviesViewModel.State) {
    when (state) {
        is Loading -> MoviesLoadingScreen()
        is Error -> MoviesErrorScreen()
        is Success -> MoviesSuccessScreen()
    }
}

@Composable
fun MoviesErrorScreen() {
    Text(text = "Error")
}

@Composable
fun MoviesLoadingScreen() {
    Text(text = "Loading")
}

@Composable
fun MoviesSuccessScreen() {
    Text(text = "Success")
}