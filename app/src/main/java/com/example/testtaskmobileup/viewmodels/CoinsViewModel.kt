package com.example.testtaskmobileup.viewmodels

import android.content.Context
import android.net.ConnectivityManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.Result
import com.example.domain.models.coins.CoinInMarket
import com.example.domain.models.coins.CoinInMarketUi
import com.example.domain.models.coins.CurrentCoinData
import com.example.domain.models.coins.CurrentCoinDataUi
import com.example.domain.models.coins.CurrentCoinDataWithContracts
import com.example.domain.usecases.interfaces.GetCoinsListUseCase
import com.example.domain.usecases.interfaces.GetCurrentCoinDataUseCase
import com.example.testtaskmobileup.presentation.constants.Currency
import com.example.testtaskmobileup.presentation.enums.ResponseState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CoinsViewModel(
    private val getCoinsListUseCase: GetCoinsListUseCase,
    private val getCurrentCoinDataUseCase: GetCurrentCoinDataUseCase
) : ViewModel() {

    //first screen
    private val _selectedCurrency = MutableStateFlow(Currency.USD.uppercase())
    val selectedCurrency: StateFlow<String> = _selectedCurrency.asStateFlow()
    fun updateSelectedCurrency(value: String) {
        _selectedCurrency.update { value }
    }

    private val _coinsList = MutableStateFlow(emptyList<CoinInMarketUi>().toMutableList())
    val coinsList: StateFlow<List<CoinInMarketUi>> = _coinsList.asStateFlow()

    //second screen
    private val currentCoin = CurrentCoinDataUi(
        id = "",
        name = "",
        image = "",
        categories = emptyList(),
        description = ""
    )
    private val _coinData = MutableStateFlow(currentCoin)
    val coinData: StateFlow<CurrentCoinDataUi> = _coinData.asStateFlow()

    //common
    private val _currentState = MutableStateFlow(ResponseState.LOADING)
    val currentState: StateFlow<ResponseState> = _currentState.asStateFlow()

    private val _connectionState = MutableStateFlow(false)
    fun updateConnectionState(value: Boolean) {
        _connectionState.update { value }
    }

    fun getCoinsList(typeCurrency: String) {
        _coinsList.value.clear()
        if (!_connectionState.value)
            _currentState.update { ResponseState.FAILURE }
        else {
            val response = getCoinsListUseCase.execute(typeCurrency = typeCurrency)
            viewModelScope.launch {
                response.collect {
                    when (it) {
                        is Result.Success<*> -> {
                            val saved = it.copy().value as List<CoinInMarket>
                            saved.forEach { coin ->
                                _coinsList.value.add(coin.toCoinInMarketUi())
                            }
                            _currentState.update { ResponseState.SUCCESS }
                        }

                        is Result.Error -> _currentState.update { ResponseState.FAILURE }

                        is Result.Loading -> _currentState.update { ResponseState.LOADING }
                    }
                }
            }
        }
    }

    fun getCoinData(id: String) {
        if (!_connectionState.value)
            _currentState.update { ResponseState.FAILURE }
        else {
            val response = getCurrentCoinDataUseCase.execute(id = id)
            viewModelScope.launch {
                response.collect {
                    when (it) {
                        is Result.Success<*> -> {
                            var first: CurrentCoinData? = null
                            var second: CurrentCoinDataWithContracts? = null
                            try {
                                first = it.copy().value as CurrentCoinData
                            } catch (e: Exception) {
                                second = it.copy().value as CurrentCoinDataWithContracts
                            }

                            _coinData.update {
                                first?.toCurrentCoinDataUi() ?: second!!.toCurrentCoinDataUi()
                            }
                            _currentState.update { ResponseState.SUCCESS }
                        }

                        is Result.Error -> _currentState.update { ResponseState.FAILURE }

                        is Result.Loading -> _currentState.update { ResponseState.LOADING }
                    }
                }
            }
        }
    }

    fun isInternetAvailable(context: Context): Boolean {
        var result = false
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        connectivityManager.activeNetworkInfo?.run {
            result = when (type) {
                ConnectivityManager.TYPE_WIFI -> true
                ConnectivityManager.TYPE_MOBILE -> true
                ConnectivityManager.TYPE_ETHERNET -> true
                else -> false
            }
        }
        return result
    }
}