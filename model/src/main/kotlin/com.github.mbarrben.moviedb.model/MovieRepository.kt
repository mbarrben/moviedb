package com.github.mbarrben.moviedb.model

import com.github.mbarrben.moviedb.model.entities.Movie
import rx.Observable

interface MovieRepository {
  fun getMovies(): Observable<Movie.List>
}
