package com.github.mbarrben.moviedb.movies.ui.composables

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Error
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.mbarrben.moviedb.R
import com.github.mbarrben.moviedb.ui.theme.MovieDbTheme

@Composable
fun MoviesErrorScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            modifier = Modifier.padding(64.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Icon(
                modifier = Modifier.size(42.dp),
                imageVector = Icons.Outlined.Error,
                tint = MaterialTheme.colors.error,
                contentDescription = null,
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = stringResource(id = R.string.movies_error),
                style = MaterialTheme.typography.h6.copy(
                    color = MaterialTheme.colors.error,
                    textAlign = TextAlign.Center,
                )
            )
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
)
@Composable
private fun DefaultPreview() {
    MovieDbTheme {
        MoviesErrorScreen()
    }
}