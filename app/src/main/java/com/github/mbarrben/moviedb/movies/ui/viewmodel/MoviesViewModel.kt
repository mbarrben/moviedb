package com.github.mbarrben.moviedb.movies.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.mbarrben.moviedb.movies.domain.GetPopularMovies
import com.github.mbarrben.moviedb.movies.domain.GetSearchMovies
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
) : ViewModel() {

    var contentState: ContentState by mutableStateOf(ContentState.Loading)
    var searchState: SearchState by mutableStateOf(SearchState.Inactive)

    private var page = 1

    private var debounceSearchJob: Job? = null

    init {
        if (contentState !is ContentState.Success) {
            refresh()
        }
    }

    fun startSearch() {
        searchState = SearchState.Active("")
    }

    fun stopSearch() {
        searchState = SearchState.Inactive
        refresh()
    }

    fun refresh() {
        resetState()
        launchRequests()
    }

    fun search(query: String) {
        searchState = SearchState.Active(query)
        debounceSearchJob?.cancel()
        debounceSearchJob = viewModelScope.launch {
            delay(SEARCH_DEBOUNCE_DELAY)
            refresh()
        }
    }

    fun loadNextPage() {
        page++
        launchRequests()
    }

    private fun resetState() {
        contentState = ContentState.Loading
        page = 1
    }

    private fun launchRequests() {
        when (val state = searchState) {
            is SearchState.Inactive -> retrieveMovies(page)
            is SearchState.Active -> {
                if (state.query.isEmpty()) {
                    retrieveMovies(page)
                } else {
                    searchMovies(state.query, page)
                }
            }
        }
    }

    private fun retrieveMovies(page: Int = 1) {
        viewModelScope.launch {
            contentState = getPopularMovies(page)
                .map { movies -> movies.map { viewModelFactory.build(it) } }
                .fold(
                    ifLeft = { ContentState.Error },
                    ifRight = { movies -> contentState + ContentState.Success(movies) }
                )
        }
    }

    private fun searchMovies(query: String, page: Int = 1) {
        viewModelScope.launch {
            contentState = getSearchMovies(query, page)
                .map { movies -> movies.map { viewModelFactory.build(it) } }
                .fold(
                    ifLeft = { ContentState.Error },
                    ifRight = { movies -> contentState + ContentState.Success(movies) }
                )
        }
    }

    private operator fun ContentState.plus(newState: ContentState.Success): ContentState.Success =
        when (this) {
            ContentState.Error -> newState
            ContentState.Loading -> newState
            is ContentState.Success -> this + newState
        }

    private operator fun ContentState.Success.plus(newState: ContentState.Success): ContentState.Success = ContentState.Success(
        movies = movies + newState.movies
    )

    sealed class ContentState {
        object Loading : ContentState()
        data class Success(val movies: List<MovieViewModel>) : ContentState()
        object Error : ContentState()
    }

    sealed class SearchState {
        object Inactive : SearchState()
        data class Active(val query: String) : SearchState()
    }

    companion object {
        const val SEARCH_DEBOUNCE_DELAY = 500L
    }
}
