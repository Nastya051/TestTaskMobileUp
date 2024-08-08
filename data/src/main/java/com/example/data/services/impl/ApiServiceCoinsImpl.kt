package com.example.data.services.impl

import com.example.data.constants.QueryParameters
import com.example.data.services.interfaces.ApiServiceCoins
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.contentType

class ApiServiceCoinsImpl(private val client: HttpClient) : ApiServiceCoins {
    private val coinsInMarket = "/api/v3/coins/markets"
    private val coinData = "/api/v3/coins/"
    override suspend fun getCoinsList(typeCurrency: String): HttpResponse {
        return client.get(coinsInMarket) {
            contentType(ContentType.Application.Json)
            parameter(QueryParameters.PER_PAGE, 25)
            parameter(QueryParameters.PAGE, 1)
            parameter(QueryParameters.CURRENCY, typeCurrency)
        }
    }

    override suspend fun getCurrentCoinData(id: String): HttpResponse {
        return client.get(coinData + id) {
            contentType(ContentType.Application.Json)
        }
    }
}