package com.github.mbarrben.moviedb.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.CompositionLocalProvider
import coil.ImageLoader
import com.github.mbarrben.moviedb.navigation.NavigationManager
import com.github.mbarrben.moviedb.navigation.composables.Navigation
import com.github.mbarrben.moviedb.ui.theme.MovieDbTheme
import com.google.accompanist.coil.LocalImageLoader
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navigationManager: NavigationManager
    @Inject
    lateinit var imageLoader: ImageLoader

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieDbTheme {
                CompositionLocalProvider(LocalImageLoader provides imageLoader) {
                    Navigation(navigationManager)
                }
            }
        }
    }
}
