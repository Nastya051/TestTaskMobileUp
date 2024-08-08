package com.example.domain.usecases.interfaces

import com.example.domain.models.Result
import kotlinx.coroutines.flow.Flow

interface GetCurrentCoinDataUseCase {
    fun execute(id: String): Flow<Result>
}