package com.example.domain.usecases.impl

import com.example.domain.models.Result
import com.example.domain.repository_interface.CoinsRepository
import com.example.domain.usecases.interfaces.GetCurrentCoinDataUseCase
import kotlinx.coroutines.flow.Flow

class GetCurrentCoinDataUseCaseImpl(private val repository: CoinsRepository) :
    GetCurrentCoinDataUseCase {
    override fun execute(id: String): Flow<Result> {
        return repository.getCurrentCoinData(id = id)
    }
}