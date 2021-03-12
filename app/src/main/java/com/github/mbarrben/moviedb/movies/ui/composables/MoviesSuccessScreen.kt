package com.github.mbarrben.moviedb.movies.ui.composables

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.mbarrben.moviedb.movies.ui.viewmodel.MovieViewModel
import com.github.mbarrben.moviedb.ui.theme.MovieDbTheme

@Composable
fun MoviesSuccessScreen(movies: List<MovieViewModel>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
    ) {
        items(items = movies) { movie ->
            Movie(
                modifier = Modifier.fillMaxWidth(),
                movie = movie,
            )
        }
    }
}

@Composable
fun Movie(modifier: Modifier = Modifier, movie: MovieViewModel) {
    Box(
        modifier = modifier
            .height(54.dp)
            .padding(horizontal = 16.dp),
        contentAlignment = Alignment.CenterStart,
    ) {
        Text(
            text = movie.title,
            style = MaterialTheme.typography.h6,
        )
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
)
@Composable
private fun DefaultPreview() {
    MovieDbTheme {
        MoviesSuccessScreen(
            listOf(
                MovieViewModel(
                    id = 0,
                    title = "Raya and the last dragon",
                    posterPath = null,
                    clickAction = {}
                ),
                MovieViewModel(
                    id = 0,
                    title = "Tom & Jerry",
                    posterPath = null,
                    clickAction = {}
                ),
                MovieViewModel(
                    id = 0,
                    title = "Coming 2 America",
                    posterPath = null,
                    clickAction = {}
                ),
                MovieViewModel(
                    id = 0,
                    title = "Monster Hunter",
                    posterPath = null,
                    clickAction = {}
                ),
                MovieViewModel(
                    id = 0,
                    title = "Wonder Woman 1984",
                    posterPath = null,
                    clickAction = {}
                ),
            )
        )
    }
}