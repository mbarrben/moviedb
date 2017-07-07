package com.github.mbarrben.moviedb.domain.movies

import com.github.mbarrben.moviedb.domain.movies.MovieMode.Popular
import com.github.mbarrben.moviedb.domain.movies.MovieMode.Search
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.junit.Test

class GetMoviesShould {

  companion object {
    val A_QUERY = "lambs"
    val A_PAGE = 1
  }

  val popularMovies = mock<PopularMovies>()
  val searchMovies = mock<SearchMovies>()

  val getMovies: GetMovies = GetMovies(popularMovies, searchMovies)

  @Test fun returnPopularMoviesWhenModeIsPopular() {
    getMovies.get(Popular, A_PAGE)

    verify(popularMovies).get(A_PAGE)
  }

  @Test fun searchMoviesWhenModeIsSearch() {
    getMovies.get(Search(A_QUERY), A_PAGE)

    verify(searchMovies).search(A_QUERY, A_PAGE)
  }

}