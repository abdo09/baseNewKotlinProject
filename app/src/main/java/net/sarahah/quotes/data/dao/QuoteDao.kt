package net.sarahah.quotes.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import net.sarahah.quotes.data.dto.QuoteResponse
import net.sarahah.quotes.data.model.Quote

@Dao
abstract class QuoteDao : BaseDao<QuoteResponse> {

    @Query("select * from ${QuoteResponse.ALL_QUOTES_TABLE_NAME}")
    abstract fun getAllQuotes(): LiveData<List<QuoteResponse>>

}