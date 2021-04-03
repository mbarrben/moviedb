package com.github.mbarrben.moviedb.movies.ui.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.github.mbarrben.moviedb.detail.DetailScreen
import com.github.mbarrben.moviedb.movies.domain.Movie
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
                    val navController = rememberNavController()

                    NavHost(navController, startDestination = "movies") {
                        composable("movies") {
                            MoviesScreen(
                                contentState = viewModel.contentState,
                                searchState = viewModel.searchState,
                                onScrollToEnd = viewModel::loadNextPage,
                                onRefresh = viewModel::refresh,
                                onStartSearch = viewModel::startSearch,
                                onStopSearch = viewModel::stopSearch,
                                onSearchValueChanged = viewModel::search,
                                onMovieSelected = { movie ->
                                    navController.navigate("detail/${movie.title}")
                                }
                            )
                        }
                        composable(
                            route = "detail/{movieTitle}",
                            arguments = listOf(navArgument("movieTitle") { type = NavType.StringType })
                        ) { backStackEntry ->
                            val movieTitle = backStackEntry.arguments?.getString("movieTitle")!!
                            DetailScreen(
                                Movie(
                                    id = 0,
                                    title = movieTitle,
                                    originalTitle = "",
                                    overview = "",
                                    releaseDate = null,
                                    originalLanguage = "",
                                    voteCount = 0,
                                    voteAverage = 0F,
                                    posterPath = null,
                                    backdropPath = null
                                )
                            )
                        }
                    }
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
            onMovieSelected = {},
        )
    }
}
