package dev.carlos.shortform.data.cloud.retrofit

import dev.carlos.shortform.data.cloud.AcronymsRemoteSource
import dev.carlos.shortform.data.cloud.model.ShortformRemote
import io.reactivex.Single

class AcronymsRemoteDatasource(private val acronymsService: AcronymsService) : AcronymsRemoteSource {
    override fun getAcronymDefinition(acronym: String): Single<List<ShortformRemote>> {
        return acronymsService.getAcronymDefinition(acronym)
    }
}
