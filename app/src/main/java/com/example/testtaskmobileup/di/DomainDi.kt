package com.example.testtaskmobileup.di

import com.example.domain.usecases.impl.GetCoinsListUseCaseImpl
import com.example.domain.usecases.impl.GetCurrentCoinDataUseCaseImpl
import com.example.domain.usecases.interfaces.GetCoinsListUseCase
import com.example.domain.usecases.interfaces.GetCurrentCoinDataUseCase
import org.koin.dsl.module

val domainModule = module {
    factory<GetCurrentCoinDataUseCase> {
        GetCurrentCoinDataUseCaseImpl(repository = get())
    }
    factory<GetCoinsListUseCase> {
        GetCoinsListUseCaseImpl(repository = get())
    }
}