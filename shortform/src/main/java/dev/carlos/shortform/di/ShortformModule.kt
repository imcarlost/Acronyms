package dev.carlos.shortform.di

import dev.carlos.core.domain.network.RemoteClient
import dev.carlos.core.scheduler.Scheduler
import dev.carlos.core.scheduler.SchedulerProvider
import dev.carlos.shortform.data.ShortformDataRepository
import dev.carlos.shortform.data.cloud.ShortformRemoteSource
import dev.carlos.shortform.data.cloud.retrofit.ShortformRemoteDatasource
import dev.carlos.shortform.data.cloud.retrofit.ShortformService
import dev.carlos.shortform.domain.ShortformRepository
import dev.carlos.shortform.domain.GetShortformDefinition
import dev.carlos.shortform.viewmodels.ShortformViewmodel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val shortformModule = module {
    single<Scheduler> { SchedulerProvider() }

    single {
        get<RemoteClient>().getClient(ShortformService::class.java)
    }

    factory<ShortformRemoteSource> { ShortformRemoteDatasource(get()) }
    factory<ShortformRepository> { ShortformDataRepository(get()) }

    factory { GetShortformDefinition(get(), get()) }

    viewModel { ShortformViewmodel(get()) }
}
