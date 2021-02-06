package net.sarahah.quotes.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import net.sarahah.quotes.model.Quote

@Database(
    entities = [Quote::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class QuoteDatabase : RoomDatabase() {

    abstract fun getArticleDao(): QuoteDao

    companion object {
        @Volatile
        private var instance: QuoteDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also { instance = it }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                QuoteDatabase::class.java,
                "article_db.db"
            ).build()
    }
}