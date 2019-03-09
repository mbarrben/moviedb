package com.github.mbarrben.moviedb.app.di

import kotlinx.coroutines.Dispatchers
import org.rewedigital.katana.createModule
import org.rewedigital.katana.dsl.compact.factory

val coroutinesModule = createModule("coroutinesModule") {
    factory("IO") {
        Dispatchers.IO
    }
}