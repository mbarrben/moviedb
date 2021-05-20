package com.github.mbarrben.moviedb.movies.domain


import com.github.mbarrben.moviedb.CommonConstants.Companion.ANY_BACKDROP_PATH
import com.github.mbarrben.moviedb.CommonConstants.Companion.ANY_ID
import com.github.mbarrben.moviedb.CommonConstants.Companion.ANY_ORIGINAL_LANGUAGE
import com.github.mbarrben.moviedb.CommonConstants.Companion.ANY_ORIGINAL_TITLE
import com.github.mbarrben.moviedb.CommonConstants.Companion.ANY_OVERVIEW
import com.github.mbarrben.moviedb.CommonConstants.Companion.ANY_POSTER_PATH
import com.github.mbarrben.moviedb.CommonConstants.Companion.ANY_RELEASE_DATE
import com.github.mbarrben.moviedb.CommonConstants.Companion.ANY_TITLE
import com.github.mbarrben.moviedb.CommonConstants.Companion.ANY_VOTE_AVERAGE
import com.github.mbarrben.moviedb.CommonConstants.Companion.ANY_VOTE_COUNT

internal class DomainMother {
    companion object {
        fun aMovie() = Movie(
            id = ANY_ID,
            title = ANY_TITLE,
            originalTitle = ANY_ORIGINAL_TITLE,
            overview = ANY_OVERVIEW,
            releaseDate = ANY_RELEASE_DATE,
            originalLanguage = ANY_ORIGINAL_LANGUAGE,
            voteCount = ANY_VOTE_COUNT,
            voteAverage = ANY_VOTE_AVERAGE,
            posterPath = ANY_POSTER_PATH,
            backdropPath = ANY_BACKDROP_PATH
        )
    }
}
