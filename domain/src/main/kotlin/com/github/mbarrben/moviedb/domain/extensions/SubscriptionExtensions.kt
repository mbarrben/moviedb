package com.github.mbarrben.moviedb.domain.extensions

import rx.Subscription
import rx.subscriptions.CompositeSubscription

/**
 * subscription -= observable.subscribe{}
 */
operator fun CompositeSubscription.minusAssign(subscription: Subscription) = remove(subscription)