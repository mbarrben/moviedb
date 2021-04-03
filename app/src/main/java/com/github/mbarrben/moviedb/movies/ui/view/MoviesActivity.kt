package com.github.mbarrben.moviedb.movies.ui.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.github.mbarrben.moviedb.movies.ui.composables.MoviesScreen
import com.github.mbarrben.moviedb.movies.ui.viewmodel.MoviesViewModel
import com.github.mbarrben.moviedb.ui.theme.MovieDbTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesActivity : ComponentActivity() {

    private val viewModel: MoviesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieDbTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    MoviesScreen(
                        contentState = viewModel.contentState,
                        searchState = viewModel.searchState,
                        onScrollToEnd = viewModel::loadNextPage,
                        onRefresh = viewModel::refresh,
                        onStartSearch = viewModel::startSearch,
                        onStopSearch = viewModel::stopSearch,
                        onSearchValueChanged = viewModel::search,
                    )
                }
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
        MoviesScreen(
            contentState = MoviesViewModel.ContentState.Loading,
            searchState = MoviesViewModel.SearchState.Inactive,
            onScrollToEnd = {},
            onRefresh = {},
            onStartSearch = {},
            onStopSearch = {},
            onSearchValueChanged = {},
        )
    }
}
