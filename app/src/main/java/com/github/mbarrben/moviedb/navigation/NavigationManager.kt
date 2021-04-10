package com.github.mbarrben.moviedb.navigation

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.referentialEqualityPolicy
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NavigationManager @Inject constructor() {
    val commands = mutableStateOf<Navigation>(Navigation.Default, policy = referentialEqualityPolicy())

    fun navigate(command: Navigation) {
        commands.value = command
    }

}