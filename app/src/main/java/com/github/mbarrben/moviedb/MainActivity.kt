package com.github.mbarrben.moviedb

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.mbarrben.moviedb.app.DependencyInjection
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.rewedigital.katana.KatanaTrait
import org.rewedigital.katana.android.modules.createActivityModule
import org.rewedigital.katana.createComponent

class MainActivity : AppCompatActivity(), KatanaTrait {

    @ExperimentalCoroutinesApi
    override val component = createComponent(
        modules = listOf(
            createActivityModule(this)
        ),
        dependsOn = listOf(DependencyInjection.applicationComponent)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
    }
}
