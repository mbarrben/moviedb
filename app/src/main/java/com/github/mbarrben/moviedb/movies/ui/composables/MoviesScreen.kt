package com.github.mbarrben.moviedb.movies.ui.composables

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.SearchOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import com.github.mbarrben.moviedb.R
import com.github.mbarrben.moviedb.movies.domain.Movie
import com.github.mbarrben.moviedb.movies.ui.viewmodel.MoviesViewModel
import com.github.mbarrben.moviedb.movies.ui.viewmodel.MoviesViewModel.ContentState.Error
import com.github.mbarrben.moviedb.movies.ui.viewmodel.MoviesViewModel.ContentState.Loading
import com.github.mbarrben.moviedb.movies.ui.viewmodel.MoviesViewModel.ContentState.Success
import com.github.mbarrben.moviedb.movies.ui.viewmodel.MoviesViewModel.SearchState.Active
import com.github.mbarrben.moviedb.movies.ui.viewmodel.MoviesViewModel.SearchState.Inactive
import com.github.mbarrben.moviedb.ui.theme.MovieDbTheme

@Composable
fun MoviesScreen(viewModel: MoviesViewModel) {
    MoviesScreen(
        contentState = viewModel.contentState,
        searchState = viewModel.searchState,
        onScrollToEnd = viewModel::loadNextPage,
        onRefresh = viewModel::refresh,
        onStartSearch = viewModel::startSearch,
        onStopSearch = viewModel::stopSearch,
        onSearchValueChanged = viewModel::search,
        onMovieSelected = { movie -> viewModel.navigateToDetail(movie) }
    )
}

@Composable
private fun MoviesScreen(
    contentState: MoviesViewModel.ContentState,
    searchState: MoviesViewModel.SearchState,
    onScrollToEnd: () -> Unit,
    onRefresh: () -> Unit,
    onStartSearch: () -> Unit,
    onStopSearch: () -> Unit,
    onSearchValueChanged: (String) -> Unit,
    onMovieSelected: (Movie) -> Unit,
) {
    Surface(color = MaterialTheme.colors.background) {
        Scaffold(
            topBar = {
                TopAppBar(
                    searchState = searchState,
                    onRefresh = onRefresh,
                    onStartSearch = onStartSearch,
                    onStopSearch = onStopSearch,
                    onSearchValueChanged = onSearchValueChanged,
                )
            }
        ) {
            when (contentState) {
                is Loading -> MoviesLoadingScreen()
                is Error -> MoviesErrorScreen()
                is Success -> MoviesSuccessScreen(
                    movies = contentState.movies,
                    onScrollToEnd = onScrollToEnd,
                    onMovieSelected = onMovieSelected,
                )
            }
        }
    }
}

@Composable
private fun TopAppBar(
    searchState: MoviesViewModel.SearchState,
    onRefresh: () -> Unit,
    onStartSearch: () -> Unit,
    onStopSearch: () -> Unit,
    onSearchValueChanged: (String) -> Unit,
) {
    when (searchState) {
        is Active -> SearchAppBar(
            onSearchValueChanged = onSearchValueChanged,
            onCancelSearch = onStopSearch,
            query = searchState.query,
        )
        is Inactive -> DefaultAppBar(
            onRefresh = onRefresh,
            onSearch = onStartSearch
        )
    }
}

@Composable
fun DefaultAppBar(
    onRefresh: () -> Unit,
    onSearch: () -> Unit,
) {
    TopAppBar(
        title = { Text(text = stringResource(id = R.string.app_name)) },
        actions = {
            IconButton(onClick = { onRefresh() }) {
                Icon(Icons.Filled.Refresh, contentDescription = stringResource(id = R.string.refresh))
            }
            IconButton(onClick = { onSearch() }) {
                Icon(Icons.Filled.Search, contentDescription = stringResource(id = R.string.search))
            }
        },
    )
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchAppBar(
    onSearchValueChanged: (String) -> Unit,
    onCancelSearch: () -> Unit,
    query: String,
) {
    TopAppBar(
        title = {
            val keyboardController = LocalSoftwareKeyboardController.current
            val focusRequester = FocusRequester()
            TextField(
                modifier = Modifier.focusRequester(focusRequester),
                value = query,
                onValueChange = onSearchValueChanged,
                colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.Transparent),
                maxLines = 1,
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = {
                    keyboardController?.hideSoftwareKeyboard()
                }),
            )
            DisposableEffect(Unit) {
                focusRequester.requestFocus()
                onDispose {}
            }
        },
        actions = {
            IconButton(onClick = { onCancelSearch() }) {
                Icon(Icons.Filled.SearchOff, contentDescription = stringResource(id = R.string.search_off))
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
            contentState = Loading,
            searchState = Inactive,
            onScrollToEnd = {},
            onRefresh = {},
            onStartSearch = {},
            onStopSearch = {},
            onSearchValueChanged = {},
            onMovieSelected = {},
        )
    }
}