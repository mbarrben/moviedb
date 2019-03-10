package com.github.mbarrben.moviedb.movies.di

import com.github.mbarrben.moviedb.app.di.CoroutinesModule.IO
import com.github.mbarrben.moviedb.app.di.NetworkModule.API_KEY
import com.github.mbarrben.moviedb.movies.data.MoviesRepository
import com.github.mbarrben.moviedb.movies.data.network.MoviesApiClient
import com.github.mbarrben.moviedb.movies.data.network.MoviesService
import com.github.mbarrben.moviedb.movies.domain.GetPopularMovies
import com.github.mbarrben.moviedb.movies.domain.NavigateToDetail
import com.github.mbarrben.moviedb.movies.ui.view.MoviesView
import com.github.mbarrben.moviedb.movies.ui.view.adapter.MoviesAdapter
import com.github.mbarrben.moviedb.movies.ui.viewmodel.MoviesViewModel
import com.github.mbarrben.moviedb.movies.ui.viewmodel.ViewModelFactory
import org.rewedigital.katana.createModule
import org.rewedigital.katana.dsl.compact.factory
import org.rewedigital.katana.dsl.get
import retrofit2.Retrofit

val moviesModule = createModule("moviesModule") {

    factory {
        get<Retrofit>().create(MoviesService::class.java)
    }

    factory { MoviesApiClient(service = get()) }

    factory {
        MoviesRepository(
            apiClient = get(),
            apiKey = get(API_KEY)
        )
    }

    factory {
        GetPopularMovies(
            moviesRepository = get(),
            context = get(IO)
        )
    }

    factory { NavigateToDetail() }

    factory { ViewModelFactory(navigateToDetail = get()) }

    factory {
        MoviesViewModel.Provider(
            getPopularMovies = get(),
            viewModelFactory = get()
        )
    }

    factory { MoviesAdapter() }

    factory {
        MoviesView(
            viewModelProvider = get(),
            moviesAdapter = get()
        )
    }
}