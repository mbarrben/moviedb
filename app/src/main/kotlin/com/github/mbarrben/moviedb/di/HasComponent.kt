package com.github.mbarrben.moviedb.di

interface HasComponent<out T> {
  val component: T
}