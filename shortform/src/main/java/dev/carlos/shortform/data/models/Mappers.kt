package dev.carlos.shortform.data.models

import dev.carlos.shortform.data.cloud.model.ShortformRemote

fun ShortformRemote.toShortformModel() = ShortformModel(this.value, this.results.map {
    ShortformModel.LongformModel(it.value, it.corpusFrequency, it.since)
})
