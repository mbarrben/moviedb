package com.github.mbarrben.moviedb.movies.ui.viewmodel

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.mbarrben.moviedb.commons.extensions.buildViewModel
import com.github.mbarrben.moviedb.movies.domain.GetPopularMovies
import kotlinx.coroutines.launch

class MoviesViewModel(
    private val getPopularMovies: GetPopularMovies,
    private val viewModelFactory: ViewModelFactory
) : ViewModel() {

    private val mutableStatus: MutableLiveData<Status> = MutableLiveData()

    val status: LiveData<Status>
        get() = mutableStatus

    fun start() {
        if (mutableStatus.value !is Status.Success) {
            mutableStatus.value = Status.Loading
            retrieveMovies()
        }
    }

    fun retry() {
        mutableStatus.value = Status.Loading
        retrieveMovies()
    }

    fun loadPage(page: Int) {
        retrieveMovies(page)
    }

    private fun retrieveMovies(page: Int = 1) {
        viewModelScope.launch {
            mutableStatus.value = doRetrieveMovies(page)
        }
    }

    private suspend fun doRetrieveMovies(page: Int): Status = getPopularMovies(page)
        .map { movies -> movies.map { viewModelFactory.build(it) } }
        .fold(
            ifLeft = { Status.Error },
            ifRight = { movies -> status + Status.Success(movies) }
        )

    private operator fun LiveData<MoviesViewModel.Status>.plus(newStatus: Status.Success): Status.Success =
        when (val currentStatus = value) {
            null -> newStatus
            Status.Error -> newStatus
            Status.Loading -> newStatus
            is Status.Success -> currentStatus + newStatus
        }

    private operator fun Status.Success.plus(newStatus: Status.Success): Status.Success = Status.Success(
        movies = movies + newStatus.movies
    )

    sealed class Status {
        object Loading : Status()
        data class Success(val movies: List<MovieViewModel>) : Status()
        object Error : Status()

        val isLoading: Boolean
            get() = this == Loading
        val isError: Boolean
            get() = this == Error
        val isSuccess: Boolean
            get() = this is Success
    }

    class Provider(
        private val getPopularMovies: GetPopularMovies,
        private val viewModelFactory: ViewModelFactory
    ) {
        fun of(fragment: Fragment) = fragment.buildViewModel {
            MoviesViewModel(
                getPopularMovies,
                viewModelFactory
            )
        }
    }
}
