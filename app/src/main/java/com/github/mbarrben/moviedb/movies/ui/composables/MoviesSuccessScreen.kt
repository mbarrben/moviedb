package com.github.mbarrben.moviedb.movies.ui.composables

import android.content.res.Configuration
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import com.github.mbarrben.moviedb.movies.domain.Movie
import com.github.mbarrben.moviedb.movies.ui.viewmodel.MovieViewModel
import com.github.mbarrben.moviedb.ui.theme.MovieDbTheme
import dev.chrisbanes.accompanist.coil.CoilImageDefaults

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MoviesSuccessScreen(
    movies: List<MovieViewModel>,
    onScrollToEnd: () -> Unit,
    onMovieSelected: (Movie) -> Unit,
) {
    LazyVerticalGrid(
        modifier = Modifier.fillMaxSize(),
        cells = GridCells.Fixed(numberOfColumns())
    ) {
        itemsIndexed(items = movies) { index, movie ->
            Movie(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(3 / 4F),
                movie = movie,
                onMovieSelected = onMovieSelected,
            )

            if (index == movies.lastIndex) {
                onScrollToEnd()
            }
        }
    }
}

@Composable
private fun numberOfColumns(): Int {
    val configuration = LocalConfiguration.current
    return when (configuration.orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> 4
        else -> 2
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
)
@Composable
private fun DefaultPreview() {
    val movie = Movie(
        id = 0,
        title = "",
        originalTitle = "",
        overview = "",
        releaseDate = null,
        originalLanguage = "",
        voteCount = 0,
        voteAverage = 0F,
        posterPath = null,
        backdropPath = null
    )
    MovieDbTheme {
        MoviesSuccessScreen(
            listOf(
                MovieViewModel(
                    id = 0,
                    title = "Raya and the last dragon",
                    posterPath = null,
                    imageLoader = CoilImageDefaults.defaultImageLoader(),
                    model = movie,
                ),
                MovieViewModel(
                    id = 0,
                    title = "Miraculous World: New York, United HeroeZ",
                    posterPath = null,
                    imageLoader = CoilImageDefaults.defaultImageLoader(),
                    model = movie,
                ),
                MovieViewModel(
                    id = 0,
                    title = "Coming 2 America",
                    posterPath = null,
                    imageLoader = CoilImageDefaults.defaultImageLoader(),
                    model = movie,
                ),
                MovieViewModel(
                    id = 0,
                    title = "Monster Hunter",
                    posterPath = null,
                    imageLoader = CoilImageDefaults.defaultImageLoader(),
                    model = movie,
                ),
                MovieViewModel(
                    id = 0,
                    title = "Wonder Woman 1984",
                    posterPath = null,
                    imageLoader = CoilImageDefaults.defaultImageLoader(),
                    model = movie,
                ),
            ),
            onScrollToEnd = {},
            onMovieSelected = {},
        )
    }
}