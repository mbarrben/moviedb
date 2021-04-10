package com.github.mbarrben.moviedb.navigation

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.referentialEqualityPolicy

class NavigationManager {

    val commands = mutableStateOf<Navigation>(Navigation.Default, policy = referentialEqualityPolicy())

    fun navigate(command: Navigation) {
        commands.value = command
    }
}