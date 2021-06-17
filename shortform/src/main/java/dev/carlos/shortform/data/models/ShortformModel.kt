package dev.carlos.shortform.data.models

data class ShortformModel(
    val value: String,
    val results: List<LongformModel>
) {
    data class LongformModel(
        val value: String,
        val corpusFrequency: Int,
        val since: Int
    )
}
