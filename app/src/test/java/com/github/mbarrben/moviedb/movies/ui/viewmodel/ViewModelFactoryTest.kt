package com.github.mbarrben.moviedb.movies.ui.viewmodel

import android.view.View
import com.github.mbarrben.moviedb.movies.CommonConstants.Companion.ANY_ID
import com.github.mbarrben.moviedb.movies.CommonConstants.Companion.ANY_POSTER_PATH
import com.github.mbarrben.moviedb.movies.CommonConstants.Companion.ANY_TITLE
import com.github.mbarrben.moviedb.movies.domain.DomainMother
import com.github.mbarrben.moviedb.movies.domain.NavigateToDetail
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.*
import org.junit.Test

class ViewModelFactoryTest {
    private val navigateToDetailMock: NavigateToDetail = mock()

    private val sut = ViewModelFactory(
        navigateToDetail = navigateToDetailMock
    )

    @Test
    fun `Returns a view model with correct fields when builds ir from a movie`() {
        val result = sut.build(ANY_MOVIE)

        assertEquals(ANY_ID, result.id)
        assertEquals(ANY_TITLE, result.title)
        assertEquals(ANY_POSTER_PATH, result.posterPath)
    }

    @Test
    fun `Navigates to detail when executes click action`() {
        val result = sut.build(ANY_MOVIE)
        result.clickAction(ANY_VIEW)

        verify(navigateToDetailMock).invoke(ANY_VIEW, ANY_MOVIE)
    }

    private companion object {
        val ANY_MOVIE = DomainMother.aMovie()
        val ANY_VIEW: View = mock()
    }
}