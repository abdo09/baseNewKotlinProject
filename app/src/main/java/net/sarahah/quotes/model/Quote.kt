package net.sarahah.quotes.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(
    tableName = "quote"
)
data class Quote(
    var author: String? = "",
    @PrimaryKey(autoGenerate = true)
    var id: Int? = 0,
    var permalink: String? = "",
    var quote: String? = ""
) : Serializable