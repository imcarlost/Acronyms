package dev.carlos.shortform.feature

import dev.carlos.shortform.data.models.ShortformModel
import dev.carlos.shortform.domain.ShortformRepository
import io.reactivex.Single

class MockShortformRepository(
    private val searchedAcronym: String,
    private val response: List<ShortformModel.LongformModel>
) :
    ShortformRepository {
    override fun getShortformDefinition(acronym: String): Single<ShortformModel> {
        return Single.fromCallable { ShortformModel(searchedAcronym, response) }
    }
}
