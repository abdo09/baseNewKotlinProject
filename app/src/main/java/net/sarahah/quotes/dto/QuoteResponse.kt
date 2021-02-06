package net.sarahah.quotes.dto
import android.annotation.SuppressLint
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize
import android.os.Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
@JsonClass(generateAdapter = true)
data class QuoteResponse(
    @Json(name = "author")
    var author: String? = "", // Jeremy S. Anderson
    @Json(name = "id")
    var id: Int? = 0, // 34
    @Json(name = "permalink")
    var permalink: String? = "", // http://quotes.stormconsultancy.co.uk/quotes/34
    @Json(name = "quote")
    var quote: String? = "" // There are two major products that come out of Berkeley: LSD and UNIX.  We don’t believe this to be a coincidence.
) : Parcelable