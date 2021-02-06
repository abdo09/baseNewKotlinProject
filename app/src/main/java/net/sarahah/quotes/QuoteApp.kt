package net.sarahah.quotes

import net.sarahah.quotes.di.dataModule
import net.sarahah.quotes.di.koinModule
import net.sarahah.quotes.di.netWorkModule
import net.sarahah.quotes.utils.Constants
import net.sarahah.quotes.utils.LocalizationUtil

import android.content.Context
import android.content.res.Configuration
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import com.google.firebase.FirebaseApp
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class QuoteApp: MultiDexApplication() {

    override fun attachBaseContext(context: Context?) {
        super.attachBaseContext(context)
        MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()

        //firebase initialization on the app level
        FirebaseApp.initializeApp(this)

        configureKoin()

        setDefaultLanguage()

    }

    private fun configureKoin() {
        startKoin {
            // use the Android context given there
            androidContext(this@QuoteApp)
            // use AndroidLogger as Koin Logger - default Level.INFO
            androidLogger(Level.ERROR)
            // load properties from assets/koin.properties file
            androidFileProperties()

            modules(listOf(dataModule, koinModule, netWorkModule))
        }
    }

    private fun setDefaultLanguage() {
        val lang = Constants().getCurrentLanguage(applicationContext)
        LocalizationUtil.setLanguage(lang, this)
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        setDefaultLanguage()
        super.onConfigurationChanged(newConfig)
        setDefaultLanguage()
    }
}