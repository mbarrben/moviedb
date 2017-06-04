package com.github.mbarrben.moviedb.model.mappers

import com.github.mbarrben.moviedb.model.entities.Movie
import com.github.mbarrben.moviedb.model.entities.RestMovie

class MovieMapper {

  fun map(restMovie: RestMovie): Movie = with(restMovie) {
    Movie(
        id,
        title,
        originalTitle,
        overview,
        releaseDate,
        originalLanguage,
        voteCount,
        voteAverage,
        posterPath,
        backdropPath
    )
  }

}