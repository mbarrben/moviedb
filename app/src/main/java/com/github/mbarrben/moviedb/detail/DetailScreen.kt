package com.github.mbarrben.moviedb.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons.Filled
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.mbarrben.moviedb.R.string
import com.github.mbarrben.moviedb.movies.domain.Movie
import com.github.mbarrben.moviedb.ui.theme.MovieDbTheme
import com.google.accompanist.coil.rememberCoilPainter
import com.google.accompanist.imageloading.ImageLoadState.Empty
import com.google.accompanist.imageloading.ImageLoadState.Error
import com.google.accompanist.imageloading.ImageLoadState.Loading
import com.google.accompanist.imageloading.ImageLoadState.Success

@Composable
fun DetailScreen(movie: Movie) {
    Surface(color = MaterialTheme.colors.background) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = movie.title)
                    },
                    navigationIcon = {
                        IconButton(onClick = {}) {
                            Icon(Filled.ArrowBack, contentDescription = stringResource(id = string.back))
                        }
                    }
                )
            },
        ) {
            Column(
                Modifier.fillMaxSize()
            ) {
                val painter = rememberCoilPainter(
                    request = movie.posterPath,
                    fadeIn = true,
                )
                when (painter.loadState) {
                    is Success -> Image(
                        modifier = Modifier
                            .fillMaxWidth(),
                        painter = painter,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                    )
                    is Loading -> Unit
                    is Error -> Unit
                    is Empty -> Unit
                }

                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    text = movie.title,
                    style = MaterialTheme.typography.h4,
                )
            }
        }
    }
}

@Preview(
    showBackground = true,
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