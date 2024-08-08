package com.example.testtaskmobileup.di

import com.example.testtaskmobileup.viewmodels.CoinsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel<CoinsViewModel> {
        CoinsViewModel(
            getCoinsListUseCase = get(),
            getCurrentCoinDataUseCase = get()
        )
    }
}