package net.sarahah.quotes.data

import androidx.room.Database
import androidx.room.RoomDatabase
import net.sarahah.quotes.data.dao.LikedQuoteDao
import net.sarahah.quotes.data.dao.QuoteDao
import net.sarahah.quotes.data.dto.QuoteResponse
import net.sarahah.quotes.data.model.Quote

@Database(entities = [
    Quote::class,
    QuoteResponse::class
], version = 12, exportSchema = false)
abstract class QuoteDB : RoomDatabase() {
    abstract fun quoteDAO(): QuoteDao
    abstract fun likedQuoteDAO(): LikedQuoteDao
}