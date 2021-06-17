package dev.carlos.shortform.domain

import dev.carlos.shortform.data.models.ShortformModel
import io.reactivex.Flowable
import io.reactivex.Single

interface AcronymsRepository {
    fun getAcronymDefinition(acronym: String): Single<ShortformModel>
}
