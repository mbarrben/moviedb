package com.github.mbarrben.moviedb.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.github.mbarrben.moviedb.navigation.NavigationManager
import com.github.mbarrben.moviedb.navigation.composables.Navigation
import com.github.mbarrben.moviedb.ui.theme.MovieDbTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navigationManager: NavigationManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieDbTheme {
                Navigation(navigationManager)
            }
        }
    }
}
