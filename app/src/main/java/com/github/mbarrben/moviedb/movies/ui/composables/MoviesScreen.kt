package com.github.mbarrben.moviedb.movies.ui.composables

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.github.mbarrben.moviedb.R
import com.github.mbarrben.moviedb.movies.ui.viewmodel.MoviesViewModel
import com.github.mbarrben.moviedb.movies.ui.viewmodel.MoviesViewModel.State.Error
import com.github.mbarrben.moviedb.movies.ui.viewmodel.MoviesViewModel.State.Loading
import com.github.mbarrben.moviedb.movies.ui.viewmodel.MoviesViewModel.State.Success
import com.github.mbarrben.moviedb.ui.theme.MovieDbTheme

@Composable
fun MoviesScreen(
    state: MoviesViewModel.State,
    onScrollToEnd: () -> Unit,
    onRefresh: () -> Unit,
) {
    Scaffold(
        topBar = { TopAppBar(onRefresh = onRefresh) }
    ) {
        when (state) {
            is Loading -> MoviesLoadingScreen()
            is Error -> MoviesErrorScreen()
            is Success -> MoviesSuccessScreen(
                movies = state.movies,
                onScrollToEnd = onScrollToEnd,
            )
        }
    }
}

@Composable
private fun TopAppBar(
    onRefresh: () -> Unit,
) {
    TopAppBar(
        title = { Text(text = stringResource(id = R.string.app_name)) },
        actions = {
            IconButton(
                onClick = { onRefresh() }
            ) {
                Icon(Icons.Filled.Refresh, contentDescription = stringResource(id = R.string.refresh))
            }
        },
    )
}

@Preview(
    showBackground = true,
)
@Composable
private fun DefaultPreview() {
    MovieDbTheme {
        MoviesScreen(
            state = Loading,
            onScrollToEnd = {},
            onRefresh = {},
        )
    }
}