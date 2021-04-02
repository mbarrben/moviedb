package com.github.mbarrben.moviedb.movies.ui.viewmodel

import arrow.core.left
import com.github.mbarrben.moviedb.TestCoroutinesRule
import com.github.mbarrben.moviedb.movies.domain.Error
import com.github.mbarrben.moviedb.movies.domain.GetPopularMovies
import com.github.mbarrben.moviedb.movies.domain.GetSearchMovies
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.never
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MoviesViewModelTest {
    @get:Rule
    val testCoroutinesRule = TestCoroutinesRule()

    private val getPopularMoviesMock: GetPopularMovies = mock {
        onBlocking { invoke(any()) } doReturn Error.left()
    }
    private val getSearchMoviesMock: GetSearchMovies = mock {
        onBlocking { invoke(any(), any()) } doReturn Error.left()
    }
    private val viewModelFactoryMock: ViewModelFactory = mock()

    private val sut by lazy {
        MoviesViewModel(
            getPopularMovies = getPopularMoviesMock,
            getSearchMovies = getSearchMoviesMock,
            viewModelFactory = viewModelFactoryMock,
        )
    }

    @Test
    fun `Search only last query when time between search calls is shorter than debounce time`() =
        testCoroutinesRule.testDispatcher.runBlockingTest {
            val firstQuery = "first"
            val secondQuery = "second"
            whenever(getPopularMoviesMock(any())).thenReturn(Error.left())

            sut.search(firstQuery)
            advanceTimeBy(MoviesViewModel.SEARCH_DEBOUNCE_DELAY - 100L)
            sut.search(secondQuery)
            advanceTimeBy(MoviesViewModel.SEARCH_DEBOUNCE_DELAY)

            verify(getSearchMoviesMock, never()).invoke(eq(firstQuery), any())
            verify(getSearchMoviesMock, times(1)).invoke(secondQuery, 1)
        }

    @Test
    fun `Search every query when time between search calls is larger than debounce time`() =
        testCoroutinesRule.testDispatcher.runBlockingTest {
            val firstQuery = "first"
            val secondQuery = "second"
            whenever(getPopularMoviesMock(any())).thenReturn(Error.left())

            sut.search(firstQuery)
            advanceTimeBy(MoviesViewModel.SEARCH_DEBOUNCE_DELAY + 100L)
            sut.search(secondQuery)
            advanceTimeBy(MoviesViewModel.SEARCH_DEBOUNCE_DELAY)

            verify(getSearchMoviesMock).invoke(firstQuery, 1)
            verify(getSearchMoviesMock).invoke(secondQuery, 1)
        }
}