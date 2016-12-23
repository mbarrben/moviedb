package com.github.mbarrben.moviedb

import android.support.test.InstrumentationRegistry.getInstrumentation
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.intent.rule.IntentsTestRule
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.filters.LargeTest
import android.support.test.runner.AndroidJUnit4
import android.support.test.runner.AndroidJUnitRunner
import com.github.mbarrben.moviedb.di.ApplicationComponent
import com.github.mbarrben.moviedb.di.ApplicationModule
import com.github.mbarrben.moviedb.domain.movies.GetMovies
import com.github.mbarrben.moviedb.movies.di.MoviesListModule
import com.github.mbarrben.moviedb.movies.view.platform.MoviesActivity
import com.github.mbarrben.moviedb.utils.RecyclerViewItemCountAssertion
import it.cosenonjaviste.daggermock.DaggerMockRule
import org.amshove.kluent.When
import org.amshove.kluent.calling
import org.amshove.kluent.itReturns
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations.initMocks
import rx.lang.kotlin.emptyObservable

@RunWith(AndroidJUnit4::class) @LargeTest class MoviesActivityShould {

  @JvmField @Rule var daggerRule: DaggerMockRule<ApplicationComponent> = DaggerMockRule(
      ApplicationComponent::class.java,
      ApplicationModule(getInstrumentation().targetContext),
      MoviesListModule()
  ).set { component ->
    val app = getInstrumentation().targetContext.applicationContext as MovieDbApp
    app.applicationComponent = component
  }

  @get:Rule val activityRule: IntentsTestRule<MoviesActivity> = IntentsTestRule(MoviesActivity::class.java, true, false)

  @Mock lateinit var getMovies: GetMovies

  @Before fun setup() {
    initMocks(this)
  }

  @Test fun loadAnEmptyListOfMovies() {
    When calling getMovies.get() itReturns emptyObservable()

    activityRule.launchActivity(null)

    onView(withId(R.id.moviesRecycler)).check(RecyclerViewItemCountAssertion(0))
  }

}
