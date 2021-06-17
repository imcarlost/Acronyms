package dev.carlos.shortform.data.cloud.retrofit

import dev.carlos.shortform.data.cloud.ShortformRemoteSource
import dev.carlos.shortform.data.cloud.model.ShortformRemote
import io.reactivex.Single

class ShortformRemoteDatasource(private val shortformService: ShortformService) : ShortformRemoteSource {
    override fun getShortformDefinition(acronym: String): Single<List<ShortformRemote>> {
        return shortformService.getShortformDefinition(acronym)
    }
}
