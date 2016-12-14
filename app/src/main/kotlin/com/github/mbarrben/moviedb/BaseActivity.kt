package com.github.mbarrben.moviedb

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity
import com.github.mbarrben.moviedb.di.ActivityModule

abstract class BaseActivity(@LayoutRes val layoutRes: Int) : AppCompatActivity() {

  val applicationComponent by lazy { (application as MovieDbApp).applicationComponent }
  val activityModule by lazy { ActivityModule(this) }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(layoutRes)
    applicationComponent.inject(this)
    inject()
  }

  abstract fun inject()
}
