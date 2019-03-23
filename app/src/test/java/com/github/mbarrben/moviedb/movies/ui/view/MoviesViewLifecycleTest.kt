package com.github.mbarrben.moviedb.movies.ui.view

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Lifecycle.Event.ON_CREATE
import androidx.lifecycle.Lifecycle.Event.ON_DESTROY
import androidx.lifecycle.Lifecycle.Event.ON_PAUSE
import androidx.lifecycle.Lifecycle.Event.ON_RESUME
import androidx.lifecycle.Lifecycle.Event.ON_START
import androidx.lifecycle.Lifecycle.Event.ON_STOP
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.MutableLiveData
import com.github.mbarrben.moviedb.movies.ui.view.adapter.MoviesAdapter
import com.github.mbarrben.moviedb.movies.ui.viewmodel.MovieViewModel
import com.github.mbarrben.moviedb.movies.ui.viewmodel.MoviesViewModel
import com.github.mbarrben.moviedb.movies.ui.viewmodel.MoviesViewModel.Status.Error
import com.github.mbarrben.moviedb.movies.ui.viewmodel.MoviesViewModel.Status.Loading
import com.github.mbarrben.moviedb.movies.ui.viewmodel.MoviesViewModel.Status.Success
import com.github.mbarrben.moviedb.movies.ui.viewmodel.ViewModelMother
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.never
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.junit.runners.Parameterized.Parameters

@RunWith(Parameterized::class)
class MoviesViewLifecycleTest(
    private var event: Lifecycle.Event,
    private var status: MoviesViewModel.Status,
    private var expected: List<MovieViewModel>?
) {
    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    private val fragmentMock: Fragment = mock()

    private val viewModelStatus: MutableLiveData<MoviesViewModel.Status> = MutableLiveData()

    private val viewModelMock: MoviesViewModel = mock {
        on { status } doReturn viewModelStatus
    }
    private val viewModelProviderMock: MoviesViewModel.Provider = mock {
        on { of(fragmentMock) } doReturn viewModelMock
    }
    private val moviesAdapterMock: MoviesAdapter = mock()
    private val bindingMock: MoviesFragmentBindingAdapter = mock {
        on { moviesRecycler } doReturn mock()
    }

    private val sut = MoviesView(
        viewModelProvider = viewModelProviderMock,
        moviesAdapter = moviesAdapterMock
    )

    @Test
    fun `Swaps adapter when onCreate and `() {
        givenAFragment(event)

        whenOnCreate()
        whenViewModelStatusChangesTo(status)

        expected?.let { thenSwapsAdapter(it) } ?: thenNeverSwapsAdapter()
    }

    private fun givenAFragment(event: Lifecycle.Event) {
        val lifecycleRegistry = LifecycleRegistry(fragmentMock)
        whenever(fragmentMock.lifecycle).thenReturn(lifecycleRegistry)
        lifecycleRegistry.handleLifecycleEvent(event)
    }

    private fun whenOnCreate() {
        sut.onCreate(fragmentMock, bindingMock)
    }

    private fun whenViewModelStatusChangesTo(newStatus: MoviesViewModel.Status) {
        viewModelStatus.value = newStatus
    }

    private fun thenSwapsAdapter(movies: List<MovieViewModel>) {
        verify(moviesAdapterMock).movies = movies
    }

    private fun thenNeverSwapsAdapter() {
        verify(moviesAdapterMock, never()).movies = any()
    }

    private companion object {
        val ANY_MOVIE_VIEW_MODEL = ViewModelMother.aMovie()

        val ANY_MOVIE_VIEW_MODEL_LIST = listOf(
            ANY_MOVIE_VIEW_MODEL,
            ANY_MOVIE_VIEW_MODEL,
            ANY_MOVIE_VIEW_MODEL,
            ANY_MOVIE_VIEW_MODEL
        )

        @Parameters(name = "event = {0}, status = {1}, expected = {2}")
        @JvmStatic
        fun data() = listOf(
            arrayOf(
                ON_CREATE,
                Success(ANY_MOVIE_VIEW_MODEL_LIST),
                null
            ),
            arrayOf(
                ON_START,
                Success(ANY_MOVIE_VIEW_MODEL_LIST),
                ANY_MOVIE_VIEW_MODEL_LIST
            ),
            arrayOf(
                ON_RESUME,
                Success(ANY_MOVIE_VIEW_MODEL_LIST),
                ANY_MOVIE_VIEW_MODEL_LIST
            ),
            arrayOf(
                ON_PAUSE,
                Success(ANY_MOVIE_VIEW_MODEL_LIST),
                ANY_MOVIE_VIEW_MODEL_LIST
            ),
            arrayOf(
                ON_STOP,
                Success(ANY_MOVIE_VIEW_MODEL_LIST),
                null
            ),
            arrayOf(
                ON_DESTROY,
                Success(ANY_MOVIE_VIEW_MODEL_LIST),
                null
            ),
            arrayOf(
                ON_CREATE,
                Error,
                null
            ),
            arrayOf(
                ON_START,
                Error,
                null
            ),
            arrayOf(
                ON_RESUME,
                Error,
                null
            ),
            arrayOf(
                ON_PAUSE,
                Error,
                null
            ),
            arrayOf(
                ON_STOP,
                Error,
                null
            ),
            arrayOf(
                ON_DESTROY,
                Error,
                null
            ),
            arrayOf(
                ON_CREATE,
                Loading,
                null
            ),
            arrayOf(
                ON_START,
                Loading,
                null
            ),
            arrayOf(
                ON_RESUME,
                Loading,
                null
            ),
            arrayOf(
                ON_PAUSE,
                Loading,
                null
            ),
            arrayOf(
                ON_STOP,
                Loading,
                null
            ),
            arrayOf(
                ON_DESTROY,
                Loading,
                null
            )
        )
    }
}