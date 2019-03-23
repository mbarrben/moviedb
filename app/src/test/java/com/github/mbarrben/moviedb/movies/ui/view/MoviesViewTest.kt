package com.github.mbarrben.moviedb.movies.ui.view

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.github.mbarrben.moviedb.movies.ui.view.adapter.MoviesAdapter
import com.github.mbarrben.moviedb.movies.ui.viewmodel.MovieViewModel
import com.github.mbarrben.moviedb.movies.ui.viewmodel.MoviesViewModel
import com.github.mbarrben.moviedb.movies.ui.viewmodel.MoviesViewModel.Status.Success
import com.github.mbarrben.moviedb.movies.ui.viewmodel.ViewModelMother
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.never
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MoviesViewTest {

    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    private val fragmentMock: Fragment = mock()

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

    @Before
    fun setup() {
        givenAFragment(Lifecycle.Event.ON_DESTROY)
    }

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

    @Test
    fun `Swaps adapter when onCreate and lifecycle is Started and viewModel status changes to Success`() {
        givenAFragment(Lifecycle.Event.ON_START)

        whenOnCreate()
        whenViewModelStatusChangesTo(Success(ANY_MOVIE_VIEW_MODEL_LIST))

        thenSwapsAdapter(ANY_MOVIE_VIEW_MODEL_LIST)
    }

    @Test
    fun `Does not swap adapter when onCreate and lifecycle is Stopped and viewModel status changes to Success`() {
        givenAFragment(Lifecycle.Event.ON_STOP)

        whenOnCreate()
        whenViewModelStatusChangesTo(Success(ANY_MOVIE_VIEW_MODEL_LIST))

        thenNeverSwapsAdapter()
    }

    private fun givenAFragment(event: Lifecycle.Event) {
        val lifecycleRegistry = LifecycleRegistry(fragmentMock)
        whenever(fragmentMock.lifecycle).thenReturn(lifecycleRegistry)
        lifecycleRegistry.handleLifecycleEvent(event)
    }

    private fun whenOnCreate() {
        sut.onCreate(fragmentMock, bindingMock)
    }

    private fun whenViewModelStatusChangesTo(newStatus: MoviesViewModel.Status) {
        viewModelStatus.value = newStatus
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

    private fun thenSwapsAdapter(movies: List<MovieViewModel>) {
        verify(moviesAdapterMock).movies = movies
    }

    private fun thenNeverSwapsAdapter() {
        verify(moviesAdapterMock, never()).movies = any()
    }

    private companion object {
        val ANY_MOVIE_VIEW_MODEL = ViewModelMother.aMovie()

        val ANY_MOVIE_VIEW_MODEL_LIST = listOf(
            ANY_MOVIE_VIEW_MODEL,
            ANY_MOVIE_VIEW_MODEL,
            ANY_MOVIE_VIEW_MODEL,
            ANY_MOVIE_VIEW_MODEL
        )
    }
}