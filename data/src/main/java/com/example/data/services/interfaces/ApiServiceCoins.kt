package com.example.data.services.interfaces

import io.ktor.client.statement.HttpResponse

interface ApiServiceCoins {
    suspend fun getCoinsList(typeCurrency: String): HttpResponse
    suspend fun getCurrentCoinData(id: String): HttpResponse
}