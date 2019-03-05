package com.github.mbarrben.moviedb.movies.viewmodel

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.mbarrben.moviedb.commons.buildViewModel
import com.github.mbarrben.moviedb.movies.domain.GetPopularMovies
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

@ExperimentalCoroutinesApi
class MoviesViewModel(
    private val getPopularMovies: GetPopularMovies = GetPopularMovies(),
    private val context: CoroutineContext = Dispatchers.Default,
    private val viewModelFactory: ViewModelFactory = ViewModelFactory()
) : ViewModel() {

    private val mutableStatus: MutableLiveData<Status> = MutableLiveData()

    val status: LiveData<Status>
        get() = mutableStatus

    init {
        retrieveMovies()
    }

    fun retry() {
        retrieveMovies()
    }

    private fun retrieveMovies() {
        mutableStatus.value = Status.Loading

        viewModelScope.launch {
            val result = withContext(context) { getPopularMovies() }

            mutableStatus.value = result.map { movies -> movies.map { viewModelFactory.build(it) } }
                .fold(
                    ifLeft = { Status.Error },
                    ifRight = { movies ->
                        Status.Success(movies)
                    }
                )
        }
    }

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

    class Provider {
        fun of(fragment: Fragment) = fragment.buildViewModel {
            MoviesViewModel()
        }
    }
}

