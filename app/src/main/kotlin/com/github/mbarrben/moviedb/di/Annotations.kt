package com.github.mbarrben.moviedb.di

import javax.inject.Qualifier
import javax.inject.Scope
import kotlin.annotation.AnnotationRetention.RUNTIME

@Qualifier @Retention(RUNTIME) annotation class UIScheduler
@Qualifier @Retention(RUNTIME) annotation class IOScheduler

@Scope @Retention annotation class PerActivity
