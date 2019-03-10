package com.github.mbarrben.moviedb.app.di

import com.github.mbarrben.moviedb.app.di.CoroutinesModule.IO
import kotlinx.coroutines.Dispatchers
import org.rewedigital.katana.createModule
import org.rewedigital.katana.dsl.compact.factory

object CoroutinesModule {
    const val IO = "IO"
}

val coroutinesModule = createModule("coroutinesModule") {
    factory(IO) {
        Dispatchers.IO
    }
}