package com.github.mbarrben.moviedb.domain.movies

import com.github.mbarrben.moviedb.domain.movies.MovieMode.Popular
import com.github.mbarrben.moviedb.domain.movies.MovieMode.Search
import com.github.mbarrben.moviedb.model.entities.Movie.List
import io.reactivex.Observable

class GetMovies(val popularMovies: PopularMovies, val searchMovies: SearchMovies) {

  fun get(mode: MovieMode, page: Int = 1): Observable<List> = when (mode) {
    is Popular -> popularMovies.get(page)
    is Search  -> searchMovies.search(mode.query, page)
  }
}