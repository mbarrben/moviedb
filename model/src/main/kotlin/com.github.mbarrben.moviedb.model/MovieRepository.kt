package com.github.mbarrben.moviedb.model

import com.github.mbarrben.moviedb.model.entities.Movie
import io.reactivex.Observable

interface MovieRepository {
  fun popular(page: Int = 1): Observable<Movie.List>
  fun search(query: String, page: Int): Observable<Movie.List>
  fun details(id: Long): Observable<Movie.Details>
}
