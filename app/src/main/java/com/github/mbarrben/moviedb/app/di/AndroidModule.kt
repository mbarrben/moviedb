package com.github.mbarrben.moviedb.app.di

import android.content.Context
import org.rewedigital.katana.android.modules.APPLICATION_CONTEXT
import org.rewedigital.katana.createModule
import org.rewedigital.katana.dsl.compact.factory
import org.rewedigital.katana.dsl.get

val androidModule = createModule {
    factory { get<Context>(APPLICATION_CONTEXT).resources }
}