package net.sarahah.quotes.di

import net.sarahah.quotes.data.repository.QuoteRepository
import net.sarahah.quotes.data.repository.OnlineQuoteRepositoryImp
import net.sarahah.quotes.ui.bottom_tabs.QuotesViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module


val koinModule = module {

    // Tells Koin how to create an instance of UserRepository
    factory<QuoteRepository> { OnlineQuoteRepositoryImp(get(), get()) }

    viewModel { QuotesViewModel(get())}
}