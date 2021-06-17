package dev.carlos.shortform.domain

import dev.carlos.shortform.data.models.ShortformModel
import dev.carlos.core.extensions.runOnIo
import dev.carlos.core.scheduler.Scheduler
import io.reactivex.Single

class GetShortformDefinition(
    private val shortformRepository: ShortformRepository,
    private val scheduler: Scheduler
) {
    fun getShortformDefinition(shortform: String): Single<ShortformModel> {
        return shortformRepository.getShortformDefinition(shortform).runOnIo(scheduler)
    }
}
