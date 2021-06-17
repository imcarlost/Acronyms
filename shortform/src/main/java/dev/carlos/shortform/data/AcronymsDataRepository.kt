package dev.carlos.shortform.data

import dev.carlos.shortform.data.cloud.AcronymsRemoteSource
import dev.carlos.shortform.data.models.ShortformModel
import dev.carlos.shortform.data.models.toShortformModel
import dev.carlos.shortform.domain.AcronymsRepository
import io.reactivex.Single

class AcronymsDataRepository(
    private val remoteDatasource: AcronymsRemoteSource
) : AcronymsRepository {

    override fun getAcronymDefinition(acronym: String): Single<ShortformModel> {
        return remoteDatasource.getAcronymDefinition(acronym).map { it.single()?.toShortformModel() }
    }
}
