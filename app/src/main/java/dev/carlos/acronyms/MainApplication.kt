package dev.carlos.acronyms

import android.app.Application
import dev.carlos.shortform.di.shortformModule
import dev.carlos.acronyms.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

@Suppress("unused")
class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        setupLogger()
        setupDi()
    }

    private fun setupLogger() {
        Timber.plant(Timber.DebugTree())
    }

    private fun setupDi() {
        startKoin {
            androidContext(this@MainApplication)

            modules(
                listOf(
                    appModules,
                    shortformModule
                )
            )
        }
    }
}
