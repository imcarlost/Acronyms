package dev.carlos.shortform.data.cloud

import dev.carlos.shortform.data.cloud.model.ShortformRemote
import io.reactivex.Single

interface AcronymsRemoteSource {
    fun getAcronymDefinition(acronym: String): Single<List<ShortformRemote>>
}
