package dev.carlos.shortform.data.cloud.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ShortformRemote(
    @SerializedName("sf") val value: String,
    @SerializedName("lfs") val results: List<LongformRemote>
) : Parcelable {
    @Parcelize
    data class LongformRemote(
        @SerializedName("lf") val value: String,
        @SerializedName("freq") val corpusFrequency: Int,
        @SerializedName("since") val since: Int
    ) : Parcelable
}
