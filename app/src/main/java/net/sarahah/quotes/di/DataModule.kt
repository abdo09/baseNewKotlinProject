package net.sarahah.quotes.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import net.sarahah.quotes.data.QuoteDB
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val dataModule = module {

    // Quote database instance
    single {
        Room.databaseBuilder(androidApplication(), QuoteDB::class.java,
            "quote.db")
            .fallbackToDestructiveMigration()
            //.fallbackToDestructiveMigrationOnDowngrade()
            .build()
    }

    single {
        getSharedPrefs(get())
    }

    single<SharedPreferences.Editor> {
        getSharedPrefs(get()).edit()
    }

    //todo refactor repo for both of them
    single { get<QuoteDB>().quoteDAO() }
    single { get<QuoteDB>().likedQuoteDAO() }

}

fun getSharedPrefs(context: Context): SharedPreferences {
    return context.getSharedPreferences("default", Context.MODE_PRIVATE)
}