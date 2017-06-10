package com.github.mbarrben.moviedb.domain.movies

import com.github.mbarrben.moviedb.domain.navigation.Navigator
import com.github.mbarrben.moviedb.model.entities.Movie
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import io.reactivex.Observable
import org.junit.Test
import java.util.Date

class MoviesPresenterShould {

  companion object {
    val A_MOVIE = Movie(
        id = 0,
        title = "The silence of the lambs",
        originalTitle = "The silence of the lambs",
        overview = "Serial killer blah blah blah",
        releaseDate = Date(),
        originalLanguage = "English",
        voteCount = 123,
        voteAverage = 9.2F,
        posterPathLastSegment = "/someimage.png",
        backdropPathLastSegment = "/someotherimage.png"
    )

    val A_MOVIE_LIST = Movie.List(
        page = 1,
        movies = listOf(A_MOVIE)
    )

    val A_QUERY = "lambs"
  }

  val getMovies = mock<GetMovies> {
    on { get() } doReturn Observable.empty<Movie.List>()
  }
  val searchMovies = mock<SearchMovies> {
    on { search(any<String>(), any<Int>()) } doReturn Observable.empty<Movie.List>()
  }
  val view = mock<MoviesView> {
    on { paginationEvents() } doReturn Observable.empty<Int>()
    on { movieClicks() } doReturn Observable.empty<Movie>()
    on { searchClosed() } doReturn Observable.empty<Unit>()
    on { searchQueries() } doReturn Observable.empty<String>()
  }
  val navigator = mock<Navigator>()

  val presenter = MoviesPresenter(getMovies, searchMovies, navigator)

  @Test fun getMoviesIsCalledOnceWhenViewIsBound() {
    presenter.bind(view)

    verify(getMovies).get()
  }

  @Test fun showErrorWhenGetMoviesReturnsError() {
    given { getMovies.get(any<Int>()) }.will { Observable.error<Movie.List>(Exception()) }

    presenter.bind(view)

    verify(view).showError()
  }

  @Test fun showMoviesWhenGetMoviesReturnsMovieList() {
    given { getMovies.get(any<Int>()) }.will { Observable.just(A_MOVIE_LIST) }

    presenter.bind(view)

    verify(view).showMovies(A_MOVIE_LIST)
  }

  @Test fun navigateToMovieDetailsWhenUserClicksOnAMovie() {
    given { view.movieClicks() }.will { Observable.just(A_MOVIE) }

    presenter.bind(view)

    verify(navigator).detail(A_MOVIE)
  }

  @Test fun searchMoviesWhenUserSearchesAQuery() {
    given { view.searchQueries() }.will { Observable.just(A_QUERY) }

    presenter.bind(view)

    verify(searchMovies).search(A_QUERY)
  }

  @Test fun loadNextPageOfMoviesWhenUserScrollsAllTheWayDown() {
    given { view.paginationEvents() }.will { Observable.just(2) }
    given { getMovies.get(any<Int>()) }.will { Observable.just(A_MOVIE_LIST) }

    presenter.bind(view)

    verify(getMovies).get(1)
    verify(getMovies).get(2)
  }

  @Test fun showEmptyViewWhenGetMoviesReturnsAnEmptyList() {
    given { getMovies.get(any<Int>()) }.will { Observable.just(Movie.List.EMPTY) }

    presenter.bind(view)

    verify(view).showEmpty()
  }
}