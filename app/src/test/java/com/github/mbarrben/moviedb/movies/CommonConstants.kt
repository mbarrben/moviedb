package com.github.mbarrben.moviedb.movies

import java.util.Date

class CommonConstants {
    companion object {
        const val ANY_ID = 299537L
        const val ANY_TITLE = "Captain Marvel"
        const val ANY_ORIGINAL_TITLE = "Captain Marvel: Electric boogaloo"
        val ANY_OVERVIEW = """
                        The story follows Carol Danvers as she becomes one of the universe’s most powerful
                        heroes when Earth is caught in the middle of a galactic war between two alien races
                        """.trimIndent()
        val ANY_RELEASE_DATE = Date(0)
        const val ANY_ORIGINAL_LANGUAGE = "en"
        const val ANY_VOTE_COUNT = 1831
        const val ANY_VOTE_AVERAGE = 7.3F
        const val ANY_POSTER_PATH = """\/AtsgWhDnHTq68L0lLsUrCnM7TjG.jpg"""
        const val ANY_BACKDROP_PATH = """\/w2PMyoyLU22YvrGK3smVM9fW1jj.jpg"""
    }
}