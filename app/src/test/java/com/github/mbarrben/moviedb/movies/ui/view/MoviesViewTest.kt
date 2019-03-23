package com.github.mbarrben.moviedb.movies.ui.view

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.github.mbarrben.moviedb.movies.ui.view.adapter.MoviesAdapter
import com.github.mbarrben.moviedb.movies.ui.viewmodel.MoviesViewModel
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.junit.Rule
import org.junit.Test

class MoviesViewTest {

    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    private val fragmentMock: Fragment = mock {
        val lifecycleRegistry = LifecycleRegistry(mock)
        on { lifecycle } doReturn lifecycleRegistry
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    }

    private val viewModelStatus: MutableLiveData<MoviesViewModel.Status> = MutableLiveData()

    private val viewModelMock: MoviesViewModel = mock {
        on { status } doReturn viewModelStatus
    }
    private val viewModelProviderMock: MoviesViewModel.Provider = mock {
        on { of(fragmentMock) } doReturn viewModelMock
    }
    private val moviesAdapterMock: MoviesAdapter = mock()
    private val moviesRecyclerMock: RecyclerView = mock()
    private val bindingMock: MoviesFragmentBindingAdapter = mock {
        on { moviesRecycler } doReturn moviesRecyclerMock
    }

    private val sut = MoviesView(
        viewModelProvider = viewModelProviderMock,
        moviesAdapter = moviesAdapterMock
    )

    @Test
    fun `Starts view model when onCreate`() {
        whenOnCreate()

        thenStartsViewModel()
    }

    @Test
    fun `Initializes binding when onCreate`() {
        whenOnCreate()

        thenInitializesBinding()
    }

    @Test
    fun `Initializes RecyclerView when onCreate`() {
        whenOnCreate()

        thenInitializesRecyclerView()
    }

    private fun whenOnCreate() {
        sut.onCreate(fragmentMock, bindingMock)
    }

    private fun thenStartsViewModel() {
        verify(viewModelMock).start()
    }

    private fun thenInitializesBinding() {
        verify(bindingMock).lifecycleOwner = fragmentMock
        verify(bindingMock).viewModel = viewModelMock
    }

    private fun thenInitializesRecyclerView() {
        verify(moviesRecyclerMock).setHasFixedSize(true)
        verify(moviesRecyclerMock).adapter = moviesAdapterMock
    }
}