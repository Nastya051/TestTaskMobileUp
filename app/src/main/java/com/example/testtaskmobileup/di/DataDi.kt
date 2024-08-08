package com.example.testtaskmobileup.di

import com.example.data.client.httpClientAndroid
import com.example.data.repository_impl.CoinsRepositoryImpl
import com.example.data.services.impl.ApiServiceCoinsImpl
import com.example.data.services.interfaces.ApiServiceCoins
import com.example.domain.repository_interface.CoinsRepository
import io.ktor.client.HttpClient
import org.koin.dsl.module

val dataModule = module {
    single<CoinsRepository> {
        CoinsRepositoryImpl(apiService = get())
    }
    single<ApiServiceCoins> {
        ApiServiceCoinsImpl(client = get())
    }
    single {
        provideHttpClient()
    }
}

fun provideHttpClient(): HttpClient {
    return httpClientAndroid
}