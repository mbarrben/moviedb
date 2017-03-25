package com.github.mbarrben.moviedb.movieDetail.view

import android.content.Context
import android.support.design.widget.CoordinatorLayout
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.text.format.DateFormat.getMediumDateFormat
import android.text.util.Linkify.WEB_URLS
import android.util.AttributeSet
import android.view.View
import com.github.mbarrben.moviedb.R
import com.github.mbarrben.moviedb.domain.moviesDetail.DetailView
import com.github.mbarrben.moviedb.extensions.getComponent
import com.github.mbarrben.moviedb.extensions.hide
import com.github.mbarrben.moviedb.extensions.inflate
import com.github.mbarrben.moviedb.extensions.linkify
import com.github.mbarrben.moviedb.extensions.load
import com.github.mbarrben.moviedb.extensions.onEnterTransitionEnd
import com.github.mbarrben.moviedb.extensions.show
import com.github.mbarrben.moviedb.extensions.transitionName
import com.github.mbarrben.moviedb.misc.emojiFlag
import com.github.mbarrben.moviedb.model.entities.Movie
import com.github.mbarrben.moviedb.movies.di.MovieDetailComponent
import com.jakewharton.rxbinding2.view.clicks
import com.squareup.picasso.Picasso
import io.reactivex.subjects.PublishSubject
import java.text.NumberFormat
import javax.inject.Inject
import kotlinx.android.synthetic.main.detail_content.view.detail_backdrop as backdrop
import kotlinx.android.synthetic.main.detail_content.view.detail_budget as budget
import kotlinx.android.synthetic.main.detail_content.view.detail_companies as companies
import kotlinx.android.synthetic.main.detail_content.view.detail_countries as countries
import kotlinx.android.synthetic.main.detail_content.view.detail_genres as genres
import kotlinx.android.synthetic.main.detail_content.view.detail_homepage as homepage
import kotlinx.android.synthetic.main.detail_content.view.detail_languages as languages
import kotlinx.android.synthetic.main.detail_content.view.detail_overview as overview
import kotlinx.android.synthetic.main.detail_content.view.detail_rating_bar as ratingBar
import kotlinx.android.synthetic.main.detail_content.view.detail_release_date as releaseDate
import kotlinx.android.synthetic.main.detail_content.view.detail_release_status as releaseStatus
import kotlinx.android.synthetic.main.detail_content.view.detail_revenue as revenue
import kotlinx.android.synthetic.main.detail_content.view.detail_runtime as runtime
import kotlinx.android.synthetic.main.detail_content.view.detail_tagline as tagline
import kotlinx.android.synthetic.main.detail_content.view.detail_title_budget as budgetTitle
import kotlinx.android.synthetic.main.detail_content.view.detail_title_companies as companiesTitle
import kotlinx.android.synthetic.main.detail_content.view.detail_title_genres as genresTitle
import kotlinx.android.synthetic.main.detail_content.view.detail_title_languages as languagesTitle
import kotlinx.android.synthetic.main.detail_content.view.detail_title_revenue as revenueTitle
import kotlinx.android.synthetic.main.detail_content.view.detail_title_runtime as runtimeTitle
import kotlinx.android.synthetic.main.detail_content.view.detail_votes as votes
import kotlinx.android.synthetic.main.detail_view.view.detail_collapsing_toolbar as collapsingToolbar
import kotlinx.android.synthetic.main.detail_view.view.detail_fab as fab
import kotlinx.android.synthetic.main.detail_view.view.detail_poster as poster
import kotlinx.android.synthetic.main.detail_view.view.detail_toolbar as toolbar

class DetailLayout
@JvmOverloads constructor(context: Context?,
                          attrs: AttributeSet? = null,
                          defStyleAttr: Int = 0) : CoordinatorLayout(context, attrs, defStyleAttr),
                                                   DetailView {

  @Inject lateinit var picasso: Picasso

  val activity: AppCompatActivity = (context as AppCompatActivity).apply {
    setSupportActionBar(toolbar)
    supportActionBar?.setDisplayHomeAsUpEnabled(true)
    supportPostponeEnterTransition()
    onEnterTransitionEnd { viewLoaded.onNext(Unit) }
  }

  val viewLoaded: PublishSubject<Unit> = PublishSubject.create<Unit>()

  private val detailViews: List<View>

  init {
    inflate(R.layout.detail_view, attachToRoot = true)
    detailViews = listOf(budgetTitle,
                         budget,
                         revenueTitle,
                         revenue,
                         companiesTitle,
                         companies,
                         countries,
                         languagesTitle,
                         languages,
                         genresTitle,
                         genres,
                         tagline,
                         homepage,
                         runtimeTitle,
                         runtime,
                         releaseStatus)
    inject()
  }

  override fun render(movie: Movie) {
    detailViews.hide()

    collapsingToolbar.title = movie.title
    overview.text = movie.overview
    releaseDate.text = getMediumDateFormat(context).format(movie.releaseDate)
    ratingBar.rating = movie.voteAverage / 2
    votes.text = context.resources.getQuantityString(R.plurals.vote_count, movie.voteCount, movie.voteCount)

    poster.contentDescription = movie.title
    poster.transitionName("transition")
    poster.load(picasso, movie.posterPath()) { activity.supportStartPostponedEnterTransition() }

    backdrop.load(picasso, movie.backdropPath())

    fab.clicks().subscribe {
      Snackbar.make(fab, "Replace with your own action ${movie.title}", Snackbar.LENGTH_LONG)
          .setAction("Action", null)
          .show()
    }
  }

  override fun details(details: Movie.Details) {
    budget.text = NumberFormat.getCurrencyInstance().format(details.budget)
    revenue.text = NumberFormat.getCurrencyInstance().format(details.revenue)
    companies.text = details.productionCompanies.joinToString(postfix = ".") { it.name }
    countries.text = details.productionCountries.joinToString(separator = " ") { emojiFlag(it.iso) }
    languages.text = details.spokenLanguages.joinToString(postfix = ".") { it.name }
    genres.text = details.genres.joinToString(postfix = ".") { it.name }
    tagline.text = details.tagline
    homepage.text = details.homepage
    homepage.linkify(WEB_URLS)
    runtime.text = context.getString(R.string.detail_runtime_minutes, details.runtime)
    releaseStatus.text = details.status

    detailViews.show()
  }

  override fun loaded() = viewLoaded

  private fun inject() = getComponent(MovieDetailComponent::class.java).inject(this)
}