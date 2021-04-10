package com.github.mbarrben.moviedb.navigation

import androidx.navigation.NavType
import androidx.navigation.compose.NamedNavArgument
import androidx.navigation.compose.navArgument

interface NavigationDirections {
    val route: String
    val arguments: List<NamedNavArgument>
}

sealed class Navigation(
    val destination: String,
) {
    object Default : Navigation(""), NavigationDirections {
        override val route = destination
        override val arguments: List<NamedNavArgument> = emptyList()
    }

    object Movies : Navigation("movies"), NavigationDirections {
        override val route = destination
        override val arguments: List<NamedNavArgument> = emptyList()
    }

    data class Detail(val title: String) : Navigation("detail/$title") {
        companion object : NavigationDirections {
            const val KEY_TITLE = "title"
            override val route = "detail/{$KEY_TITLE}"
            override val arguments = listOf(navArgument(KEY_TITLE) { type = NavType.StringType })
        }
    }
}