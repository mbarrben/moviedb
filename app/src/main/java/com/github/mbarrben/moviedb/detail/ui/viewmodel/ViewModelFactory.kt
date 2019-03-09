package com.github.mbarrben.moviedb.detail.ui.viewmodel

import com.github.mbarrben.moviedb.detail.domain.Detail
import com.github.mbarrben.moviedb.movies.domain.Movie

class ViewModelFactory {
    fun build(movie: Movie, detail: Detail? = null) = MovieDetailViewModel(
        title = movie.title,
        originalTitle = movie.originalTitle,
        overview = movie.overview,
        releaseDate = movie.releaseDate.toString(),
        originalLanguage = movie.originalLanguage,
        voteCount = movie.voteCount.toString(),
        voteAverage = movie.voteAverage,
        voteAverageText = movie.voteAverage.toString(),
        posterPath = movie.posterPath,
        backdropPath = movie.backdropPath,
        budget = detail?.budget.toString(),
        genres = detail?.genres?.joinToString(),
        homepage = detail?.homepage,
        productionCompanies = detail?.productionCompanies?.joinToString(),
        productionCountries = detail?.productionCountries?.joinToString(),
        spokenLanguages = detail?.spokenLanguages?.joinToString(),
        revenue = detail?.revenue.toString(),
        runtime = detail?.runtime.toString(),
        status = detail?.status,
        tagline = detail?.tagline
    )
}