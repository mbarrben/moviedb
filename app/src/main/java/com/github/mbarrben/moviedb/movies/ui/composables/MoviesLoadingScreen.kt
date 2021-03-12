package com.github.mbarrben.moviedb.movies.ui.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.github.mbarrben.moviedb.ui.theme.MovieDbTheme

@Composable
fun MoviesLoadingScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        CircularProgressIndicator()
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
)
@Composable
private fun DefaultPreview() {
    MovieDbTheme {
        MoviesLoadingScreen()
    }
}