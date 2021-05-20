package com.github.mbarrben.moviedb.movies.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.BrokenImage
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import com.github.mbarrben.moviedb.movies.domain.Movie
import com.github.mbarrben.moviedb.movies.ui.viewmodel.MovieViewModel
import com.github.mbarrben.moviedb.ui.theme.MovieDbTheme
import com.google.accompanist.coil.rememberCoilPainter
import com.google.accompanist.imageloading.ImageLoadState.Empty
import com.google.accompanist.imageloading.ImageLoadState.Error
import com.google.accompanist.imageloading.ImageLoadState.Loading
import com.google.accompanist.imageloading.ImageLoadState.Success

@Composable
fun Movie(
    modifier: Modifier = Modifier,
    movie: MovieViewModel,
    onMovieSelected: (Movie) -> Unit,
) {
    Box(
        modifier = modifier
            .clickable { onMovieSelected(movie.model) },
    ) {
        if (movie.posterPath != null) {
            PosterMovie(
                title = movie.title,
                posterPath = movie.posterPath,
            )
        } else {
            NoPosterMovie(
                title = movie.title,
            )
        }
    }
}

@Composable
fun PosterMovie(
    modifier: Modifier = Modifier,
    title: String,
    posterPath: String,
) {
    val painter = rememberCoilPainter(
        request = posterPath,
        fadeIn = true,
    )
    when (painter.loadState) {
        is Success -> Unit
        is Loading -> NoPosterMovie(title = title)
        is Error -> NoPosterMovie(title = title)
        is Empty -> NoPosterMovie(title = title)
    }
    Image(
        modifier = modifier
            .fillMaxSize(),
        painter = painter,
        contentDescription = title,
        contentScale = ContentScale.Crop,
    )
}

@Composable
fun NoPosterMovie(modifier: Modifier = Modifier, title: String) {
    Box(
        modifier = modifier
            .padding(8.dp)
            .fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Icon(
                modifier = Modifier.size(32.dp),
                imageVector = Icons.Outlined.BrokenImage,
                contentDescription = null,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = title,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.caption
            )
        }
    }
}

@Preview(
    showBackground = true,
)
@Composable
private fun DefaultPreview() {
    MovieDbTheme {
        NoPosterMovie(
            title = "Miraculous World: New York, United HeroeZ"
        )
    }
}