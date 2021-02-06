package net.sarahah.quotes.di

import android.content.Context
import android.content.SharedPreferences
import org.koin.dsl.module

val dataModule = module {

    single {
        getSharedPrefs(get())
    }

    single<SharedPreferences.Editor> {
        getSharedPrefs(get()).edit()
    }

}

fun getSharedPrefs(context: Context): SharedPreferences {
    return context.getSharedPreferences("default", Context.MODE_PRIVATE)
}