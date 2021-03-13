package com.github.mbarrben.moviedb.movies.ui.composables

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import com.github.mbarrben.moviedb.movies.ui.viewmodel.MovieViewModel
import com.github.mbarrben.moviedb.ui.theme.MovieDbTheme
import dev.chrisbanes.accompanist.coil.CoilImage

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MoviesSuccessScreen(movies: List<MovieViewModel>) {
    LazyVerticalGrid(
        modifier = Modifier.fillMaxSize(),
        cells = GridCells.Fixed(2),
    ) {
        items(items = movies) { movie ->
            Movie(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(3 / 4F),
                movie = movie,
            )
        }
    }
}

@Composable
fun Movie(modifier: Modifier = Modifier, movie: MovieViewModel) {
    CoilImage(
        modifier = modifier,
        data = movie.posterPath!!,
        contentDescription = movie.title,
        contentScale = ContentScale.Crop,
        fadeIn = true,
    )
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