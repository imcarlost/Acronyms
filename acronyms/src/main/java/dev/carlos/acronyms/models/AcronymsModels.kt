package dev.carlos.acronyms.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
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

@Entity(tableName = ShortformEntity.TABLE_NAME, indices = [Index(value = ["value"], unique = true)])
@TypeConverters(LongformListConverter::class)
data class ShortformEntity(
    @PrimaryKey
    val value: String,
    val results: List<LongformEntity>
) {
    data class LongformEntity(
        val value: String,
        val corpusFrequency: Int,
        val since: Int
    )

    companion object {
        const val TABLE_NAME = "shortforms"
    }
}

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
