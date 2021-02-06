package net.sarahah.quotes.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import net.sarahah.quotes.data.model.Quote

@Dao
abstract class LikedQuoteDao : BaseDao<Quote> {
    @Query("SELECT * FROM ${Quote.ALL_LIKED_TABLE_NAME}")
    abstract suspend fun getAllLikedQuotes(): LiveData<List<Quote>>

    @Query("DELETE FROM ${Quote.ALL_LIKED_TABLE_NAME} where id= :id")
    internal abstract suspend fun deleteLikedQuotes(id: String): String

}