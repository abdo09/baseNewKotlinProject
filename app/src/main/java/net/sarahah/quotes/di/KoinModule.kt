package net.sarahah.quotes.di

import org.koin.dsl.module


val koinModule = module {

    // Tells Koin how to create an instance of UserRepository
    /*factory<UserRepository> { UserRepositoryImp(get(), get(), get()) }

    viewModel { RequestViewModel(get()) }*/
}