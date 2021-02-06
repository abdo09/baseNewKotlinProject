package net.sarahah.quotes.data.model

import android.os.Parcelable
import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

@Entity(
    tableName = Quote.ALL_LIKED_TABLE_NAME
)
@Parcelize
@Keep
data class Quote(
    var author: String? = "",
    @PrimaryKey(autoGenerate = true)
    var id: Int? = 0,
    var permalink: String? = "",
    var quote: String? = ""
) : Parcelable {
    companion object {
        const val ALL_LIKED_TABLE_NAME = "liked_quote_table"
    }
}