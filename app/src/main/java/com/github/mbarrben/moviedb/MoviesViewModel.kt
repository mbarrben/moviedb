package com.github.mbarrben.moviedb

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.mbarrben.moviedb.commons.buildViewModel
import com.github.mbarrben.moviedb.network.model.Dto
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
    private val context: CoroutineContext = Dispatchers.Default
) : ViewModel(),
    CoroutineScope by MainScope() {

    private val mutableStatus: MutableLiveData<Status> = MutableLiveData()

    val status: LiveData<Status>
        get() = mutableStatus

    init {
        mutableStatus.value = Status.Loading

        launch {
            val result = withContext(context) { getPopularMovies() }

            mutableStatus.value = result.fold(
                ifLeft = { error ->
                    when (error) {
                        Dto.Error.NoResultFound -> Status.NoMoviesFound
                        Dto.Error.NoInternetConnection -> Status.ConnectionError
                    }
                },
                ifRight = { movies ->
                    Status.Success(movies)
                }
            )
        }
    }

    sealed class Status {
        object Loading : Status()
        data class Success(val movies: List<Dto.Movie>) : Status()
        object NoMoviesFound : Status()
        object ConnectionError : Status()
    }

    class Provider {
        fun of(fragment: Fragment) = fragment.buildViewModel {
            MoviesViewModel()
        }
    }
}

