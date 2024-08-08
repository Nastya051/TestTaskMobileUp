package com.example.domain.usecases.impl

import com.example.domain.models.Result
import com.example.domain.repository_interface.CoinsRepository
import com.example.domain.usecases.interfaces.GetCoinsListUseCase
import kotlinx.coroutines.flow.Flow

class GetCoinsListUseCaseImpl(private val repository: CoinsRepository) : GetCoinsListUseCase {
    override fun execute(typeCurrency: String): Flow<Result> {
        return repository.getCoinsList(typeCurrency = typeCurrency)
    }
}