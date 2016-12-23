package com.github.mbarrben.moviedb.utils

import android.support.test.espresso.NoMatchingViewException
import android.support.test.espresso.ViewAssertion
import android.support.v7.widget.RecyclerView
import android.view.View
import org.hamcrest.Matcher
import org.hamcrest.Matchers.`is`
import org.junit.Assert.assertThat


class RecyclerViewItemCountAssertion(val matcher: Matcher<Int>) : ViewAssertion {

  constructor(expectedCount: Int) : this(`is`(expectedCount))

  override fun check(view: View, noViewFoundException: NoMatchingViewException?) {
    if (noViewFoundException != null) {
      throw noViewFoundException
    }

    val adapter = (view as RecyclerView).adapter
    assertThat(adapter.itemCount, matcher)
  }

}