package com.github.mbarrben.moviedb.movies.di

import com.github.mbarrben.moviedb.BuildConfig
import com.github.mbarrben.moviedb.movies.data.MoviesRepository
import com.github.mbarrben.moviedb.movies.data.network.DateAdapter
import com.github.mbarrben.moviedb.movies.data.network.DefaultHeadersInterceptor
import com.github.mbarrben.moviedb.movies.data.network.ImageUrlAdapter
import com.github.mbarrben.moviedb.movies.data.network.MoviesDatabaseApiClient
import com.github.mbarrben.moviedb.movies.data.network.MoviesDatabaseService
import com.github.mbarrben.moviedb.movies.domain.GetPopularMovies
import com.github.mbarrben.moviedb.movies.domain.NavigateToDetail
import com.github.mbarrben.moviedb.movies.view.MoviesAdapter
import com.github.mbarrben.moviedb.movies.view.MoviesView
import com.github.mbarrben.moviedb.movies.viewmodel.MoviesViewModel
import com.github.mbarrben.moviedb.movies.viewmodel.ViewModelFactory
import com.squareup.moshi.Moshi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.rewedigital.katana.createModule
import org.rewedigital.katana.dsl.compact.factory
import org.rewedigital.katana.dsl.compact.singleton
import org.rewedigital.katana.dsl.get
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import timber.log.Timber
import java.io.File
import kotlin.coroutines.CoroutineContext

@ExperimentalCoroutinesApi
val moviesModule = createModule("moviesModule") {

    factory {
        val service: MoviesDatabaseService = get()
        MoviesDatabaseApiClient(service)
    }

    factory("API_KEY") {
        BuildConfig.API_KEY
    }

    factory {
        val apiClient: MoviesDatabaseApiClient = get()
        val apiKey: String = get("API_KEY")

        MoviesRepository(
            apiClient,
            apiKey
        )
    }

    factory {
        val repository: MoviesRepository = get()
        val context: CoroutineContext = get("IO")

        GetPopularMovies(
            repository,
            context
        )
    }

    factory {
        NavigateToDetail()
    }

    factory {
        val navigateToDetail: NavigateToDetail = get()
        ViewModelFactory(navigateToDetail)
    }

    factory {
        val getPopularMovies: GetPopularMovies = get()
        val viewModelFactory: ViewModelFactory = get()

        MoviesViewModel.Provider(
            getPopularMovies,
            viewModelFactory
        )
    }

    factory {
        MoviesAdapter()
    }

    factory {
        val viewModelProvider: MoviesViewModel.Provider = get()
        val adapter: MoviesAdapter = get()

        MoviesView(
            viewModelProvider,
            adapter
        )
    }
}