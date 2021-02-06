package net.sarahah.quotes.di

import net.sarahah.quotes.data.client.createHttpClient
import net.sarahah.quotes.data.client.createRetrofit
import net.sarahah.quotes.data.client.createWebService
import net.sarahah.quotes.data.services.QuoteServices
import org.koin.dsl.module

val netWorkModule = module{
    //create webservices definitions

    single {
        createRetrofit(get())
    }

    single {
        createHttpClient()
    }

    factory { createWebService<QuoteServices>(get()) }

}