package com.github.mbarrben.moviedb.detail.di

import com.github.mbarrben.moviedb.app.di.CoroutinesModule.IO
import com.github.mbarrben.moviedb.app.di.NetworkModule.API_KEY
import com.github.mbarrben.moviedb.detail.data.DetailRepository
import com.github.mbarrben.moviedb.detail.data.network.DetailApiClient
import com.github.mbarrben.moviedb.detail.data.network.DetailService
import com.github.mbarrben.moviedb.detail.domain.GetDetail
import com.github.mbarrben.moviedb.detail.ui.view.DetailView
import com.github.mbarrben.moviedb.detail.ui.viewmodel.DetailViewModel
import com.github.mbarrben.moviedb.detail.ui.viewmodel.ViewModelFactory
import org.rewedigital.katana.createModule
import org.rewedigital.katana.dsl.compact.factory
import org.rewedigital.katana.dsl.get
import retrofit2.Retrofit

val detailModule = createModule("detailModule") {

    factory {
        get<Retrofit>().create(DetailService::class.java)
    }

    factory { DetailApiClient(service = get()) }

    factory {
        DetailRepository(
            apiClient = get(),
            apiKey = get(API_KEY)
        )
    }

    factory {
        GetDetail(
            repository = get(),
            context = get(IO)
        )
    }

    factory { ViewModelFactory() }

    factory {
        DetailViewModel.Provider(
            getDetail = get(),
            viewModelFactory = get()
        )
    }

    factory { DetailView(viewModelProvider = get()) }
}