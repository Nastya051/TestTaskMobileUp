package com.example.domain.repository_interface

import com.example.domain.models.Result
import kotlinx.coroutines.flow.Flow

interface CoinsRepository {

    fun getCoinsList(typeCurrency: String): Flow<Result>

    fun getCurrentCoinData(id: String): Flow<Result>
}