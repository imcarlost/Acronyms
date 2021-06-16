package dev.carlos.acronyms.di

import dev.carlos.acronyms.BuildConfig
import dev.carlos.acronyms.viewmodel.NavigationViewmodel
import dev.carlos.core.domain.network.RemoteClient
import dev.carlos.core.navigation.NavigationRouter
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModules = module {
    // Retrofit
    single { RemoteClient(BuildConfig.BASE_ENDPOINT) }

    // Navigation
    single { NavigationRouter() }
    viewModel { NavigationViewmodel() }
}
