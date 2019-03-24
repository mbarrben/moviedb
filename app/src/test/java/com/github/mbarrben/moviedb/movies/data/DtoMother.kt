package com.github.mbarrben.moviedb.movies.data

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
import com.github.mbarrben.moviedb.movies.data.network.model.Dto
import com.github.mbarrben.moviedb.movies.data.network.model.ImageUrl

internal class DtoMother {
    companion object {
        fun aMovieDto() = Dto.Movie(
            id = ANY_ID,
            title = ANY_TITLE,
            originalTitle = ANY_ORIGINAL_TITLE,
            overview = ANY_OVERVIEW,
            releaseDate = ANY_RELEASE_DATE,
            originalLanguage = ANY_ORIGINAL_LANGUAGE,
            voteCount = ANY_VOTE_COUNT,
            voteAverage = ANY_VOTE_AVERAGE,
            posterPath = ImageUrl(ANY_POSTER_PATH),
            backdropPath = ImageUrl(ANY_BACKDROP_PATH)
        )
    }
}
