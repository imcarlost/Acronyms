package dev.carlos.shortform.domain

import dev.carlos.shortform.data.models.ShortformModel
import dev.carlos.core.extensions.runOnIo
import dev.carlos.core.scheduler.Scheduler
import io.reactivex.Single

class GetAcronymDefinition(
    private val acronymsRepository: AcronymsRepository,
    private val scheduler: Scheduler
) {
    fun getAcronymDefinition(acronym: String): Single<ShortformModel> {
        return acronymsRepository.getAcronymDefinition(acronym).runOnIo(scheduler)
    }
}
