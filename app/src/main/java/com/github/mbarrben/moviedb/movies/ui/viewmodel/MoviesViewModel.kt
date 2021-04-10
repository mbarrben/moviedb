package com.github.mbarrben.moviedb.movies.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.mbarrben.moviedb.movies.domain.GetPopularMovies
import com.github.mbarrben.moviedb.movies.domain.GetSearchMovies
import com.github.mbarrben.moviedb.movies.domain.Movie
import com.github.mbarrben.moviedb.movies.ui.viewmodel.MoviesViewModel.ContentState.Error
import com.github.mbarrben.moviedb.movies.ui.viewmodel.MoviesViewModel.ContentState.Loading
import com.github.mbarrben.moviedb.movies.ui.viewmodel.MoviesViewModel.ContentState.Success
import com.github.mbarrben.moviedb.movies.ui.viewmodel.MoviesViewModel.SearchState.Active
import com.github.mbarrben.moviedb.movies.ui.viewmodel.MoviesViewModel.SearchState.Inactive
import com.github.mbarrben.moviedb.navigation.Navigation
import com.github.mbarrben.moviedb.navigation.NavigationManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val getPopularMovies: GetPopularMovies,
    private val getSearchMovies: GetSearchMovies,
    private val viewModelFactory: ViewModelFactory,
    private val navigationManager: NavigationManager,
) : ViewModel() {

    var contentState: ContentState by mutableStateOf(Loading)
    var searchState: SearchState by mutableStateOf(Inactive)

    private var debounceSearchJob: Job? = null

    init {
        if (contentState !is Success) {
            refresh()
        }
    }

    fun startSearch() {
        searchState = Active("")
    }

    fun stopSearch() {
        debounceSearchJob?.cancel()
        searchState = Inactive
        refresh()
    }

    fun refresh() {
        resetState()
        retrieveMovies(page = 1)
    }

    fun search(query: String) {
        searchState = Active(query)
        debounceSearchJob?.cancel()
        debounceSearchJob = viewModelScope.launch {
            delay(SEARCH_DEBOUNCE_DELAY)
            resetState()
            searchMovies(query, page = 1)
        }
    }

    fun loadNextPage() {
        val nextPage: Int = when (val state = contentState) {
            Error -> 1
            Loading -> 1
            is Success -> state.page + 1
        }

        when (val state = searchState) {
            is Inactive -> retrieveMovies(nextPage)
            is Active -> {
                if (state.query.isEmpty()) {
                    retrieveMovies(nextPage)
                } else {
                    searchMovies(state.query, nextPage)
                }
            }
        }
    }

    fun navigateToDetail(movie: Movie) {
        navigationManager.navigate(Navigation.Detail(movie.title))
    }

    private fun resetState() {
        contentState = Loading
    }

    private fun retrieveMovies(page: Int) {
        viewModelScope.launch {
            contentState = getPopularMovies(page)
                .map { content ->
                    Success(
                        page = content.page,
                        movies = content.movies.map { movie -> viewModelFactory.build(movie) },
                    )
                }
                .fold(
                    ifLeft = { Error },
                    ifRight = { newState -> contentState + newState }
                )
        }
    }

    private fun searchMovies(query: String, page: Int) {
        viewModelScope.launch {
            contentState = getSearchMovies(query, page)
                .map { content ->
                    Success(
                        page = content.page,
                        movies = content.movies.map { movie -> viewModelFactory.build(movie) },
                    )
                }
                .fold(
                    ifLeft = { Error },
                    ifRight = { newState -> contentState + newState }
                )
        }
    }

    private operator fun ContentState.plus(newState: Success): Success =
        when (this) {
            Error -> newState
            Loading -> newState
            is Success -> this + newState
        }

    private operator fun Success.plus(newState: Success): Success = Success(
        page = newState.page,
        movies = movies + newState.movies
    )

    sealed class ContentState {
        object Loading : ContentState()
        object Error : ContentState()
        data class Success(
            val page: Int,
            val movies: List<MovieViewModel>,
        ) : ContentState()
    }

    sealed class SearchState {
        object Inactive : SearchState()
        data class Active(val query: String) : SearchState()
    }

    companion object {
        const val SEARCH_DEBOUNCE_DELAY = 500L
    }
}
