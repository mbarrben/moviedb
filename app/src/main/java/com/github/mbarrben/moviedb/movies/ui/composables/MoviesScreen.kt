package com.github.mbarrben.moviedb.movies.ui.composables

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.github.mbarrben.moviedb.movies.ui.viewmodel.MoviesViewModel
import com.github.mbarrben.moviedb.movies.ui.viewmodel.MoviesViewModel.State.*
import com.github.mbarrben.moviedb.ui.theme.MovieDbTheme

@Composable
fun MoviesScreen(state: MoviesViewModel.State) {
    when (state) {
        is Loading -> MoviesLoadingScreen()
        is Error -> MoviesErrorScreen()
        is Success -> MoviesSuccessScreen()
    }
}

@Composable
fun MoviesSuccessScreen() {
    Text(text = "Success")
}

@Preview(
    showBackground = true,
    showSystemUi = true,
)
@Composable
private fun DefaultPreview() {
    MovieDbTheme {
        MoviesScreen(state = Loading)
    }
}