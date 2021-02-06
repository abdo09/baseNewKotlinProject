package net.sarahah.quotes.utils

import android.content.Context
import net.sarahah.quotes.R
import net.sarahah.quotes.di.getSharedPrefs

class Constants {

    fun getCurrentLanguage(context: Context): String? {
        return getSharedPrefs(context).getString(context.getString(R.string.pref_language), "en")
    }

    fun setCurrentLanguage(context: Context, lang: String) {
        getSharedPrefs(context).edit().putString(context.getString(R.string.pref_language), lang).apply()
    }

    class URLS {
        companion object {
            const val BASE_URL = "http://quotes.stormconsultancy.co.uk/"
        }
    }
}

