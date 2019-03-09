package com.github.mbarrben.moviedb.detail.ui.viewmodel

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.mbarrben.moviedb.commons.extensions.buildViewModel
import com.github.mbarrben.moviedb.detail.domain.GetDetail
import com.github.mbarrben.moviedb.movies.domain.Movie
import kotlinx.coroutines.launch

class DetailViewModel(
    private val getDetail: GetDetail,
    private val viewModelFactory: ViewModelFactory,
    private val movie: Movie
) : ViewModel() {

    private val mutableStatus: MutableLiveData<MovieDetailViewModel> = MutableLiveData()

    val status: LiveData<MovieDetailViewModel>
        get() = mutableStatus

    init {
        mutableStatus.value = viewModelFactory.build(movie)

        viewModelScope.launch {
            mutableStatus.value = doRetrieveDetail()
        }
    }

    private suspend fun doRetrieveDetail(): MovieDetailViewModel = getDetail(movie.id)
        .fold(
            ifLeft = { viewModelFactory.build(movie) },
            ifRight = { detail -> viewModelFactory.build(movie, detail) }
        )

    class Provider(
        private val getDetail: GetDetail,
        private val viewModelFactory: ViewModelFactory
    ) {
        fun of(fragment: Fragment, movie: Movie) = fragment.buildViewModel {
            DetailViewModel(
                getDetail,
                viewModelFactory,
                movie
            )
        }
    }
}