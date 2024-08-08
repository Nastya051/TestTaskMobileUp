package com.example.domain.usecases.interfaces

import com.example.domain.models.Result
import kotlinx.coroutines.flow.Flow

interface GetCoinsListUseCase {
    fun execute(typeCurrency: String): Flow<Result>
}