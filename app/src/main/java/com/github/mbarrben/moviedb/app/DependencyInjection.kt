package com.github.mbarrben.moviedb.app

import android.app.Application
import com.github.mbarrben.moviedb.app.di.androidModule
import com.github.mbarrben.moviedb.app.di.coroutinesModule
import com.github.mbarrben.moviedb.app.di.createHttpModule
import org.rewedigital.katana.Component
import org.rewedigital.katana.Katana
import org.rewedigital.katana.KatanaTrait
import org.rewedigital.katana.android.environment.AndroidEnvironmentContext
import org.rewedigital.katana.android.modules.createApplicationModule
import org.rewedigital.katana.createComponent
import org.rewedigital.katana.withComponent
import timber.log.Timber

object DependencyInjection {
    lateinit var applicationComponent: Component

    internal fun init(app: Application) {
        Katana.logger = TimberKatanaLogger()
        Katana.environmentContext = AndroidEnvironmentContext()

        applicationComponent = createComponent(
            createApplicationModule(app),
            androidModule,
            coroutinesModule,
            createHttpModule(app)
        )
    }
}

private class TimberKatanaLogger : Katana.Logger {

    private val timber = Timber.tag("Katana")

    override fun debug(msg: String) = timber.d(msg)
    override fun error(msg: String, throwable: Throwable?) = timber.e(throwable, msg)
    override fun info(msg: String) = timber.i(msg)
    override fun warn(msg: String) = timber.w(msg)
}

inline fun <reified T> KatanaTrait.inject(name: String? = null) = lazy {
    withComponent { injectNow<T>(name) }
}

