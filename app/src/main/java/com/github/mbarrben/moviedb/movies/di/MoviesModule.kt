package com.github.mbarrben.moviedb.movies.di

import com.github.mbarrben.moviedb.movies.data.MoviesRepository
import com.github.mbarrben.moviedb.movies.data.network.MoviesApiClient
import com.github.mbarrben.moviedb.movies.data.network.MoviesService
import com.github.mbarrben.moviedb.movies.domain.GetPopularMovies
import com.github.mbarrben.moviedb.movies.domain.NavigateToDetail
import com.github.mbarrben.moviedb.movies.ui.view.adapter.MoviesAdapter
import com.github.mbarrben.moviedb.movies.ui.view.MoviesView
import com.github.mbarrben.moviedb.movies.ui.viewmodel.MoviesViewModel
import com.github.mbarrben.moviedb.movies.ui.viewmodel.ViewModelFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.rewedigital.katana.createModule
import org.rewedigital.katana.dsl.compact.factory
import org.rewedigital.katana.dsl.get
import retrofit2.Retrofit
import kotlin.coroutines.CoroutineContext

@ExperimentalCoroutinesApi
val moviesModule = createModule("moviesModule") {

    factory {
        val retrofit: Retrofit = get()

        retrofit.create(MoviesService::class.java)
    }

    factory {
        val service: MoviesService = get()

        MoviesApiClient(service)
    }

    factory {
        val apiClient: MoviesApiClient = get()
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