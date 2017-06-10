package com.github.mbarrben.moviedb.domain.movies

import com.github.mbarrben.moviedb.model.MovieRepository
import com.github.mbarrben.moviedb.model.entities.Movie
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import io.reactivex.schedulers.Schedulers
import org.junit.Test
import java.util.Date

class SearchMoviesShould {

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

  val repo = mock<MovieRepository> {
    on { search(any<String>(), any<Int>()) } doReturn Observable.just(A_MOVIE_LIST)
  }

  val searchMovies: SearchMovies = SearchMovies(repo, Schedulers.single(), Schedulers.single())

  @Test fun returnEmptyListWhenQueryIsEmpty() {
    val observer = TestObserver<Movie.List>()

    searchMovies.search("").subscribe(observer)

    verifyNoMoreInteractions(repo)
    observer.assertValue(Movie.List.EMPTY)
    observer.assertComplete()
  }

  @Test fun returnsMoviesFromRepoWhenQueryIsNotEmpty() {
    val observer = TestObserver<Movie.List>()

    searchMovies.search(A_QUERY, 1).subscribe(observer)

    verify(repo).search(A_QUERY, 1)
    observer.assertValue(A_MOVIE_LIST)
    observer.assertComplete()
  }
}