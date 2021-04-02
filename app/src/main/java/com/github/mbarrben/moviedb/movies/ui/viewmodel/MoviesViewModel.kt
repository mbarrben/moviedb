package com.github.mbarrben.moviedb.movies.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.mbarrben.moviedb.movies.domain.GetPopularMovies
import com.github.mbarrben.moviedb.movies.domain.GetSearchMovies
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val getPopularMovies: GetPopularMovies,
    private val getSearchMovies: GetSearchMovies,
    private val viewModelFactory: ViewModelFactory,
) : ViewModel() {

    var state: State by mutableStateOf(State.Loading)
    var query: String by mutableStateOf("")

    private var page = 1

    init {
        if (state !is State.Success) {
            refresh()
        }
    }

    fun refresh() {
        resetState()
        launchRequests()
    }

    fun search(query: String) {
        this.query = query
        resetState()
        searchMovies(query, page)
    }

    fun loadNextPage() {
        page++
        launchRequests()
    }

    private fun resetState() {
        state = State.Loading
        page = 1
    }

    private fun launchRequests() {
        if (query.isEmpty()) {
            retrieveMovies(page)
        } else {
            searchMovies(query, page)
        }
    }

    private fun retrieveMovies(page: Int = 1) {
        viewModelScope.launch {
            state = getPopularMovies(page)
                .map { movies -> movies.map { viewModelFactory.build(it) } }
                .fold(
                    ifLeft = { State.Error },
                    ifRight = { movies -> state + State.Success(movies) }
                )
        }
    }

    private fun searchMovies(query: String, page: Int = 1) {
        viewModelScope.launch {
            state = getSearchMovies(query, page)
                .map { movies -> movies.map { viewModelFactory.build(it) } }
                .fold(
                    ifLeft = { State.Error },
                    ifRight = { movies -> state + State.Success(movies) }
                )
        }
    }

    private operator fun State.plus(newState: State.Success): State.Success =
        when (this) {
            State.Error -> newState
            State.Loading -> newState
            is State.Success -> this + newState
        }

    private operator fun State.Success.plus(newState: State.Success): State.Success = State.Success(
        movies = movies + newState.movies
    )

    sealed class State {
        object Loading : State()
        data class Success(val movies: List<MovieViewModel>) : State()
        object Error : State()
    }
}
