package com.example.testtaskmobileup.presentation.ui.screens

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.domain.models.coins.CoinInMarketUi
import com.example.testtaskmobileup.R
import com.example.testtaskmobileup.presentation.constants.Currency
import com.example.testtaskmobileup.presentation.enums.ResponseState
import com.example.testtaskmobileup.presentation.navigation.LocalNavController
import com.example.testtaskmobileup.presentation.navigation.Route
import com.example.testtaskmobileup.presentation.ui.custom_views.cards.CoinInListCard
import com.example.testtaskmobileup.presentation.ui.custom_views.chips.GroupChips
import com.example.testtaskmobileup.presentation.ui.custom_views.columns.CircularLoadingAnimationColumn
import com.example.testtaskmobileup.presentation.ui.custom_views.columns.FailureColumn
import com.example.testtaskmobileup.presentation.ui.custom_views.texts.HeaderText
import com.example.testtaskmobileup.viewmodels.CoinsViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun CoinsListScreen() {
    val navController = LocalNavController.current!!
    val context = LocalContext.current
    val currencies = LocalContext.current.resources.getStringArray(R.array.currencies)
    val viewModel: CoinsViewModel = getViewModel()
    val selectedCurrency by viewModel.selectedCurrency.collectAsState()
    val state by viewModel.currentState.collectAsState()
    val coinsList by viewModel.coinsList.collectAsState()
    val isRefreshing by viewModel.isRefreshing.collectAsState()
    val wasRefreshing by viewModel.wasRefreshing.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(snackbarHost = {
        SnackbarHost(hostState = snackbarHostState) { data ->
            Snackbar(
                containerColor = MaterialTheme.colorScheme.onSecondary,
                snackbarData = data
            )
        }
    }) { paddingValues ->
        Column(
            modifier = Modifier
                .background(color = MaterialTheme.colorScheme.primary)
                .padding(paddingValues)
        ) {
            HeaderText(
                text = stringResource(id = R.string.coinsList),
                color = MaterialTheme.colorScheme.tertiary.copy(alpha = 0.87f),
                modifier = Modifier.padding(all = 16.dp)
            )
            GroupChips(
                values = currencies.toList(),
                selected = selectedCurrency,
                onClick = { viewModel.updateSelectedCurrency(it) },
                modifier = Modifier.padding(top = 16.dp, bottom = 13.dp, start = 16.dp, end = 16.dp)
            )
            HorizontalDivider(
                thickness = 1.dp,
                color = MaterialTheme.colorScheme.tertiary.copy(alpha = 0.2f),
                modifier = Modifier.shadow(elevation = 3.dp)
            )

            when (state) {
                ResponseState.LOADING -> CircularLoadingAnimationColumn()
                ResponseState.FAILURE -> {//если рефрешили, то снекбар
                    //иначе фэйл столбец
                    if (wasRefreshing) {
                        val str = stringResource(id = R.string.failOnRefresh)
                        LaunchedEffect(true) {
                            snackbarHostState.showSnackbar(str)
                        }
                        PullToRefresh(
                            isRefreshing = isRefreshing,
                            viewModel = viewModel,
                            context = context,
                            selectedCurrency = selectedCurrency,
                            currency = currencies[0],
                            coinsList = coinsList,
                            navController = navController
                        )
                    } else
                        FailureColumn(onClickButton = {
                            viewModel.updateWasRefreshing(false)
                            getCoins(
                                viewModel = viewModel,
                                context = context,
                                selectedCurrency = selectedCurrency,
                                currency = currencies[0]
                            )
                        })
                }
                ResponseState.SUCCESS -> {
                    PullToRefresh(
                        isRefreshing = isRefreshing,
                        viewModel = viewModel,
                        context = context,
                        selectedCurrency = selectedCurrency,
                        currency = currencies[0],
                        coinsList = coinsList,
                        navController = navController
                    )
                }
            }
        }
    }

    LaunchedEffect(selectedCurrency) {
        viewModel.updateWasRefreshing(false)
        getCoins(
            viewModel = viewModel,
            context = context,
            selectedCurrency = selectedCurrency,
            currency = currencies[0]
        )
    }
}

private fun getCoins(
    viewModel: CoinsViewModel,
    context: Context,
    selectedCurrency: String,
    currency: String
) {
    viewModel.updateConnectionState(viewModel.isInternetAvailable(context))
    if (selectedCurrency == currency)
        viewModel.getCoinsList(Currency.USD)
    else
        viewModel.getCoinsList(Currency.RUB)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PullToRefresh(
    isRefreshing: Boolean,
    viewModel: CoinsViewModel,
    context: Context,
    selectedCurrency: String,
    currency: String,
    coinsList: List<CoinInMarketUi>,
    navController: NavHostController
) {
    PullToRefreshBox(isRefreshing = isRefreshing, onRefresh = {
        viewModel.updateIsRefreshing(true)
        viewModel.updateWasRefreshing(true)
        getCoins(
            viewModel = viewModel,
            context = context,
            selectedCurrency = selectedCurrency,
            currency = currency
        )
        viewModel.updateIsRefreshing(false)
    }) {
        LazyColumn(
            modifier = Modifier
                .padding(top = 8.dp)
                .background(color = MaterialTheme.colorScheme.primary)
                .fillMaxSize()
        ) {
            items(coinsList.size) { coin ->
                CoinInListCard(
                    coin = coinsList[coin],
                    usd = selectedCurrency == currency,
                    onClick = {
                        navController.navigate(Route.CoinDataScreen.withArgs(coinsList[coin].id))
                    }
                )
            }
        }
    }
}