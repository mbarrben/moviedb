package com.github.mbarrben.moviedb.app

import android.os.StrictMode

internal fun initStrictMode() {
    StrictMode.setVmPolicy(
        StrictMode.VmPolicy.Builder()
            .detectActivityLeaks()
            .detectLeakedClosableObjects()
            .penaltyLog()
            .penaltyDeath()
            .build()
    )
}