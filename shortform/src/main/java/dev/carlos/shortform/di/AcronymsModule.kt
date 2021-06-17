package dev.carlos.shortform.di

import dev.carlos.core.domain.network.RemoteClient
import dev.carlos.core.scheduler.Scheduler
import dev.carlos.core.scheduler.SchedulerProvider
import dev.carlos.shortform.data.AcronymsDataRepository
import dev.carlos.shortform.data.cloud.AcronymsRemoteSource
import dev.carlos.shortform.data.cloud.retrofit.AcronymsRemoteDatasource
import dev.carlos.shortform.data.cloud.retrofit.AcronymsService
import dev.carlos.shortform.domain.AcronymsRepository
import dev.carlos.shortform.domain.GetAcronymDefinition
import org.koin.dsl.module

val acronymsModule = module {
    single<Scheduler> { SchedulerProvider() }

    single {
        get<RemoteClient>().getClient(AcronymsService::class.java)
    }

    factory<AcronymsRemoteSource> { AcronymsRemoteDatasource(get()) }
    factory<AcronymsRepository> { AcronymsDataRepository(get()) }

    factory { GetAcronymDefinition(get(), get()) }
}
