package jacs.apps.mlbucket

import android.app.Application
import jacs.apps.mlbucket.ui.audio.singingFragmentModule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class PitchEstimatorApplication : Application() {

    private val applicationScope = CoroutineScope(Dispatchers.Default)

    override fun onCreate() {
        super.onCreate()
        startKoin {
            //androidContext(applicationContext)
            androidContext(this@PitchEstimatorApplication)
            modules(
                singingFragmentModule
            )
        }

        delayedInit()
    }

    private fun delayedInit() {
        applicationScope.launch {
            Thread.sleep(1_000)
        }
    }
}