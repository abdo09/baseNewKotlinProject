package net.sarahah.quotes.db

import androidx.lifecycle.LiveData
import androidx.room.*
import net.sarahah.quotes.model.Quote

@Dao
interface QuoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(article: Quote): Long

    @Query("SELECT * FROM quote")
    fun getAllArticles(): LiveData<List<Quote>>

    @Delete
    suspend fun deleteArticle(article: Quote)
}