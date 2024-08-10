package com.example.data.repository_impl

import com.example.data.services.interfaces.ApiServiceCoins
import com.example.domain.models.Result
import com.example.domain.models.coins.CoinInMarket
import com.example.domain.models.coins.CurrentCoinData
import com.example.domain.models.coins.CurrentCoinDataWithContracts
import com.example.domain.repository_interface.CoinsRepository
import io.ktor.client.call.body
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.SerializationException

class CoinsRepositoryImpl(private val apiService: ApiServiceCoins) : CoinsRepository {
    override fun getCoinsList(typeCurrency: String): Flow<Result> {
        return flow {
            emit(Result.Loading)
            val response = apiService.getCoinsList(typeCurrency = typeCurrency)
            try {
                when (response.status.value) {
                    200 -> {
                        try {
                            emit(value = Result.Success(value = response.body<List<CoinInMarket>>()))
                        } catch (e: SerializationException) {
                            emit(value = Result.Error(value = e.toString()))
                        }
                    }

                    else -> emit(value = Result.Error(value = response.status.value.toString()))
                }
            } catch (e: Exception) {
                emit(value = Result.Error(value = e.toString()))
            }
        }
    }

    override fun getCurrentCoinData(id: String): Flow<Result> {
        return flow {
            emit(Result.Loading)
            val response = apiService.getCurrentCoinData(id = id)
            try {
                when (response.status.value) {
                    200 -> {
                        try {
                            emit(value = Result.Success(value = response.body<CurrentCoinData>()))
                        } catch (e: Exception) {
                            emit(value = Result.Success(value = response.body<CurrentCoinDataWithContracts>()))
                        }
                    }

                    else -> emit(value = Result.Error(value = response.status.value.toString()))
                }
            } catch (e: Exception) {
                emit(value = Result.Error(value = e.toString()))
            }
        }
    }
}