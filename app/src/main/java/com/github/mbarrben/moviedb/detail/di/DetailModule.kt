package com.github.mbarrben.moviedb.detail.di

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
import kotlin.coroutines.CoroutineContext

val detailModule = createModule("detailModule") {

    factory {
        val retrofit: Retrofit = get()

        retrofit.create(DetailService::class.java)
    }

    factory {
        val service: DetailService = get()

        DetailApiClient(service)
    }

    factory {
        val apiClient: DetailApiClient = get()
        val apiKey: String = get("API_KEY")

        DetailRepository(
            apiClient,
            apiKey
        )
    }

    factory {
        val repository: DetailRepository = get()
        val context: CoroutineContext = get("IO")

        GetDetail(
            repository,
            context
        )
    }

    factory {
        ViewModelFactory()
    }

    factory {
        val getDetail: GetDetail = get()
        val viewModelFactory: ViewModelFactory = get()

        DetailViewModel.Provider(
            getDetail,
            viewModelFactory
        )
    }

    factory {
        val viewModelProvider: DetailViewModel.Provider = get()

        DetailView(viewModelProvider)
    }

}