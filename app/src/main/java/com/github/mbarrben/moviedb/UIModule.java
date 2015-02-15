package com.github.mbarrben.moviedb;

import com.github.mbarrben.moviedb.di.DomainModule;

import dagger.Module;

@Module(
        complete = false,
        includes = {
                DomainModule.class
        },
        injects = {
                MainActivity.class
        })
public class UIModule {
}
