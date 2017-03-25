package com.github.mbarrben.moviedb.model

import com.github.mbarrben.moviedb.model.entities.Movie
import com.github.mbarrben.moviedb.model.entities.Movie.List
import io.reactivex.Observable

interface MovieRepository {
  fun popular(page: Int = 1): Observable<List>
  fun details(id: Long): Observable<Movie.Details>
}
