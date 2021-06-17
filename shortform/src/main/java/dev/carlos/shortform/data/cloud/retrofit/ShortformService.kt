package dev.carlos.shortform.data.cloud.retrofit

import dev.carlos.shortform.data.cloud.model.ShortformRemote
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ShortformService {
    @GET("/software/acromine/dictionary.py")
    fun getShortformDefinition(@Query("sf") acronym: String): Single<List<ShortformRemote>>
}
