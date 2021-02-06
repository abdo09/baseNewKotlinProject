package net.sarahah.quotes.data.dto
import android.annotation.SuppressLint
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@SuppressLint("ParcelCreator")
@Parcelize
@JsonClass(generateAdapter = true)
@Entity(tableName = QuoteResponse.ALL_QUOTES_TABLE_NAME)
data class QuoteResponse(
    @Json(name = "author")
    var author: String? = "", // Jeremy S. Anderson
    @Json(name = "id")
    @PrimaryKey(autoGenerate = true)
    var id: Int? = 0, // 34
    @Json(name = "permalink")
    var permalink: String? = "", // http://quotes.stormconsultancy.co.uk/quotes/34
    @Json(name = "quote")
    var quote: String? = "" // There are two major products that come out of Berkeley: LSD and UNIX.  We don’t believe this to be a coincidence.
) : Parcelable {
    companion object {
        const val ALL_QUOTES_TABLE_NAME = "all_quote_table"
    }
}