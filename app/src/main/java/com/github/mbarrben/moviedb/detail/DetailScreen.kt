package com.github.mbarrben.moviedb.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.github.mbarrben.moviedb.movies.domain.Movie
import com.github.mbarrben.moviedb.ui.theme.MovieDbTheme

@Composable
fun DetailScreen(movie: Movie) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = movie.title,
            style = MaterialTheme.typography.h6.copy(
                textAlign = TextAlign.Center,
            )
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
        DetailScreen(
            Movie(
                id = 0,
                title = "Godzilla vs. Kong",
                originalTitle = "",
                overview = "",
                releaseDate = null,
                originalLanguage = "",
                voteCount = 0,
                voteAverage = 0F,
                posterPath = "",
                backdropPath = null
            )
        )
    }
}