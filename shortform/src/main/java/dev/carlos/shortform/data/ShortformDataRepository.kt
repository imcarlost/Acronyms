package dev.carlos.shortform.data

import dev.carlos.shortform.data.cloud.ShortformRemoteSource
import dev.carlos.shortform.data.models.ShortformModel
import dev.carlos.shortform.data.models.toShortformModel
import dev.carlos.shortform.domain.ShortformRepository
import io.reactivex.Single

class ShortformDataRepository(
    private val remoteDatasource: ShortformRemoteSource
) : ShortformRepository {

    override fun getShortformDefinition(acronym: String): Single<ShortformModel> {
        return remoteDatasource.getShortformDefinition(acronym).map { it.single().toShortformModel() }
    }
}
