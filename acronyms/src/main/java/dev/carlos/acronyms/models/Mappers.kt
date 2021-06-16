package dev.carlos.acronyms.models

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class LongformListConverter {
    @TypeConverter
    fun fromString(value: String): List<ShortformEntity.LongformEntity> {
        val listType = object : TypeToken<List<ShortformEntity.LongformEntity>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromList(list: List<ShortformEntity.LongformEntity>) = Gson().toJson(list)
}

fun ShortformRemote.toShortformEntity() = ShortformEntity(this.value, this.results.map {
    ShortformEntity.LongformEntity(it.value, it.corpusFrequency, it.since)
})

fun ShortformEntity.toShortformModel() = ShortformModel(this.value, this.results.map {
    ShortformModel.LongformModel(it.value, it.corpusFrequency, it.since)
})
