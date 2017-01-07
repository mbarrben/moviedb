package com.github.mbarrben.moviedb.model

import com.github.mbarrben.moviedb.model.entities.Movie
import rx.Observable

interface MovieRepository {
  fun popular(page: Int = 1): Observable<Movie.List>
  fun details(id: Long): Observable<Movie>
}
