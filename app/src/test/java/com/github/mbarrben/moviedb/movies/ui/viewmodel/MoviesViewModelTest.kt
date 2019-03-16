package com.github.mbarrben.moviedb.movies.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.github.mbarrben.moviedb.TestCoroutinesRule
import com.github.mbarrben.moviedb.movies.CommonConstants.Companion.ANY_ID
import com.github.mbarrben.moviedb.movies.CommonConstants.Companion.ANY_POSTER_PATH
import com.github.mbarrben.moviedb.movies.CommonConstants.Companion.ANY_TITLE
import com.github.mbarrben.moviedb.movies.domain.DomainMother
import com.github.mbarrben.moviedb.movies.domain.Error
import com.github.mbarrben.moviedb.movies.domain.GetPopularMovies
import com.github.mbarrben.moviedb.movies.domain.Movie
import com.github.mbarrben.moviedb.movies.ui.viewmodel.MoviesViewModel.Status
import com.jraska.livedata.TestObserver
import com.jraska.livedata.test
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.stub
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MoviesViewModelTest {

    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()
    @get:Rule
    val testCoroutinesRule = TestCoroutinesRule()

    private val getPopularMoviesMock: GetPopularMovies = mock()
    private val viewModelFactoryMock: ViewModelFactory = mock {
        on { build(ANY_MOVIE) } doReturn ANY_MOVIE_VIEW_MODEL
    }

    private val sut = MoviesViewModel(
        getPopularMovies = getPopularMoviesMock,
        viewModelFactory = viewModelFactoryMock
    )

    @Test
    fun `Status is Success when starts and GetPopularMovies returns a list of movies`() {
        givenGetPopularMoviesReturn(ANY_MOVIE_LIST.right())

        whenStartsThen {
            assertValueHistory(
                Status.Loading,
                Status.Success(ANY_MOVIE_VIEW_MODEL_LIST)
            )
        }
    }

    @Test
    fun `Status is Loading and then Error when starts and GetPopularMovies returns an Error`() {
        givenGetPopularMoviesReturn(Error.left())

        whenStartsThen {
            assertValueHistory(
                Status.Loading,
                Status.Error
            )
        }
    }

    @Test
    fun `Only retrieves movies once when starts twice and GetPopularMovies returns a list of movies`() {
        givenGetPopularMoviesReturn(ANY_MOVIE_LIST.right())

        whenStartsTwice()

        thenRetrievesMovies(times = 1)
    }

    @Test
    fun `Retrieves movies twice when starts twice and GetPopularMovies returns an Error`() {
        givenGetPopularMoviesReturn(Error.left())

        whenStartsTwice()

        thenRetrievesMovies(times = 2)
    }

    @Test
    fun `Status is Loading and then Success when retries and GetPopularMovies returns a list of movies`() {
        givenGetPopularMoviesReturn(ANY_MOVIE_LIST.right())

        whenRetriesThen {
            assertValueHistory(
                Status.Loading,
                Status.Success(ANY_MOVIE_VIEW_MODEL_LIST)
            )
        }
    }

    @Test
    fun `Status is Loading and then Error when retries and GetPopularMovies returns an error`() {
        givenGetPopularMoviesReturn(Error.left())

        whenRetriesThen {
            assertValueHistory(
                Status.Loading,
                Status.Error
            )
        }
    }

    private fun givenGetPopularMoviesReturn(popularMovies: Either<Error, List<Movie>>) {
        getPopularMoviesMock.stub {
            onBlocking { invoke() } doReturn popularMovies
        }
    }

    private fun whenRetriesThen(asserts: TestObserver<Status>.() -> Unit) {
        val observer: TestObserver<Status> = sut.status.test()
        sut.retry()
        observer.asserts()
    }

    private fun whenStartsThen(asserts: TestObserver<Status>.() -> Unit) {
        val observer: TestObserver<Status> = sut.status.test()
        sut.start()
        observer.asserts()
    }

    private fun whenStartsTwice() {
        sut.start()
        sut.start()
    }

    private fun thenRetrievesMovies(times: Int) {
        runBlocking { verify(getPopularMoviesMock, times(times)).invoke() }
    }

    private companion object {
        val ANY_MOVIE = DomainMother.aMovie()

        val ANY_MOVIE_LIST = listOf(
            ANY_MOVIE,
            ANY_MOVIE,
            ANY_MOVIE,
            ANY_MOVIE
        )

        val ANY_MOVIE_VIEW_MODEL = MovieViewModel(
            id = ANY_ID,
            title = ANY_TITLE,
            posterPath = ANY_POSTER_PATH,
            clickAction = {}
        )

        val ANY_MOVIE_VIEW_MODEL_LIST = listOf(
            ANY_MOVIE_VIEW_MODEL,
            ANY_MOVIE_VIEW_MODEL,
            ANY_MOVIE_VIEW_MODEL,
            ANY_MOVIE_VIEW_MODEL
        )
    }
}