package com.github.mbarrben.moviedb.movies.data

import com.github.mbarrben.moviedb.movies.data.network.model.Dto
import com.github.mbarrben.moviedb.movies.data.network.model.ImageUrl
import java.util.Date

class DtoMother {
    companion object {
        fun aMovieDto() = Dto.Movie(
            id = 299537,
            title = "Captain Marvel",
            originalTitle = "Captain Marvel: Electric boogaloo",
            overview = """
                        The story follows Carol Danvers as she becomes one of the universe’s most powerful
                        heroes when Earth is caught in the middle of a galactic war between two alien races
                        """.trimIndent(),
            releaseDate = Date(0),
            originalLanguage = "en",
            voteCount = 1831,
            voteAverage = 7.3F,
            posterPath = ImageUrl("""\/AtsgWhDnHTq68L0lLsUrCnM7TjG.jpg"""),
            backdropPath = ImageUrl("""\/w2PMyoyLU22YvrGK3smVM9fW1jj.jpg""")
        )
    }
}
