package com.example.testtaskmobileup.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.CoinInMarket
import com.example.domain.models.CurrentCoinData
import com.example.domain.models.Result
import com.example.domain.usecases.interfaces.GetCoinsListUseCase
import com.example.domain.usecases.interfaces.GetCurrentCoinDataUseCase
import kotlinx.coroutines.launch

class CoinsViewModel(
    private val getCoinsListUseCase: GetCoinsListUseCase,
    private val getCurrentCoinDataUseCase: GetCurrentCoinDataUseCase
) : ViewModel() {

    init {
//        getCoinsList("usddd")
//        getCoinData("bitcoin")
    }
    fun getCoinsList(typeCurrency: String){
        val response = getCoinsListUseCase.execute(typeCurrency = typeCurrency)
        viewModelScope.launch {
            response.collect{
                when(it){
                    is Result.Success<*> ->{
                        val saved = it.copy().value as List<CoinInMarket>
                        Log.d("MyLog", "Success!!!")
                    }
                    is Result.Error -> {
                        val saved = it.copy().value
                        Log.d("MyLog", "Error: " +saved)
                    }

                    is Result.Loading -> Log.d("MyLog", "Loading...")
                }
            }
        }
    }

    fun getCoinData(id: String){
        val response = getCurrentCoinDataUseCase.execute(id = id)
        viewModelScope.launch {
            response.collect{
                when(it){
                    is Result.Success<*> ->{
                        val saved = it.copy().value as CurrentCoinData
                        Log.d("MyLog", "Success!!!")
                    }
                    is Result.Error -> {
                        val saved = it.copy().value
                        Log.d("MyLog", "Error: " +saved)
                    }
                    is Result.Loading -> Log.d("MyLog", "Loading...")
                }
            }
        }
    }
}