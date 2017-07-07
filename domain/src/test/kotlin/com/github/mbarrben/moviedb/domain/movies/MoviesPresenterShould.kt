package com.github.mbarrben.moviedb.domain.movies

import com.github.mbarrben.moviedb.domain.movies.MovieMode.Popular
import com.github.mbarrben.moviedb.domain.movies.MovieMode.Search
import com.github.mbarrben.moviedb.domain.navigation.Navigator
import com.github.mbarrben.moviedb.model.entities.Movie
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.eq
import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import io.reactivex.Observable
import io.reactivex.functions.Consumer
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

  private val getMovies = mock<GetMovies> {
    on { get(any(), any()) } doReturn Observable.empty<Movie.List>()
  }
  private val view = mock<MoviesView> {
    on { paginationEvents() } doReturn Observable.empty<Int>()
    on { movieClicks() } doReturn Observable.empty<Movie>()
    on { searchClosed() } doReturn Observable.empty<Unit>()
    on { searchQueries() } doReturn Observable.empty<String>()
  }
  private val navigator = mock<Navigator>()

  private val presenter = MoviesPresenter(getMovies, navigator)

  @Test fun subscribeToGetPopularMoviesWhenViewIsBound() {
    val observable = mock<Observable<Movie>>()
    given { getMovies.get(eq(Popular), any()) }.will { observable }

    presenter.bind(view)

    verify(observable).subscribe(any(), any())
  }

  @Test fun subscribeToPaginationEventsWhenViewIsBound() {
    val observable = mock<Observable<Int>>()
    given { view.paginationEvents() }.will { observable }

    presenter.bind(view)

    verify(observable).subscribe(any<Consumer<Int>>())
  }

  @Test fun showErrorWhenGetPopularMoviesReturnsError() {
    given { getMovies.get(eq(Popular), any()) }.will { Observable.error<Movie.List>(Exception()) }

    presenter.bind(view)

    verify(view).showError()
  }

  @Test fun showMoviesWhenGetPopularMoviesReturnsMovieList() {
    given { getMovies.get(eq(Popular), any()) }.will { Observable.just(A_MOVIE_LIST) }

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

    verify(getMovies).get(Popular)
    verify(getMovies).get(Search(A_QUERY))
  }

  @Test fun loadNextPageOfMoviesWhenUserScrollsAllTheWayDown() {
    given { view.paginationEvents() }.will { Observable.just(2) }

    presenter.bind(view)

    view.paginationEvents()
        .test()
        .assertOf { verify(getMovies).get(Popular, 2) }
  }

  @Test fun loadNextPageOfMoviesWhenUserScrollsAllTheWayDownWhileSearching() {
    given { view.searchQueries() }.will { Observable.just(A_QUERY) }
    given { view.paginationEvents() }.will { Observable.just(2) }

    presenter.bind(view)

    verify(view).searchQueries()

    view.searchQueries()
        .test()
        .assertOf { verify(getMovies).get(Search(A_QUERY)) }
        .assertOf { verify(view, times(2)).paginationEvents() }

    view.paginationEvents()
        .test()
        .assertOf { verify(getMovies).get(Search(A_QUERY), 2) }
  }

  @Test fun showEmptyViewWhenGetMoviesReturnsAnEmptyList() {
    given { getMovies.get(any(), any()) }.will { Observable.just(Movie.List.EMPTY) }

    presenter.bind(view)

    verify(view).showEmpty()
  }
}