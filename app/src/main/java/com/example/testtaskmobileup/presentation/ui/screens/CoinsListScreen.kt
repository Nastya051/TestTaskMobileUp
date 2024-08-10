package com.example.testtaskmobileup.presentation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.testtaskmobileup.R
import com.example.testtaskmobileup.presentation.enums.ResponseState
import com.example.testtaskmobileup.presentation.constants.Currency
import com.example.testtaskmobileup.presentation.navigation.LocalNavController
import com.example.testtaskmobileup.presentation.navigation.Route
import com.example.testtaskmobileup.presentation.ui.custom_views.cards.CoinInListCard
import com.example.testtaskmobileup.presentation.ui.custom_views.columns.CircularLoadingAnimationColumn
import com.example.testtaskmobileup.presentation.ui.custom_views.chips.GroupChips
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

    Column(modifier = Modifier.background(color = MaterialTheme.colorScheme.primary)) {
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
            ResponseState.FAILURE -> {
                FailureColumn(onClickButton = {
                    viewModel.updateConnectionState(viewModel.isInternetAvailable(context))
                    if (selectedCurrency == currencies[0])
                        viewModel.getCoinsList(Currency.USD)
                    else
                        viewModel.getCoinsList(Currency.RUB)
                })
            }
            ResponseState.SUCCESS -> {
                LazyColumn(
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .background(color = MaterialTheme.colorScheme.primary)
                        .fillMaxSize()
                ) {
                    items(coinsList.size) { coin ->
                        CoinInListCard(
                            imageUrl = coinsList[coin].image,
                            name = coinsList[coin].name,
                            shortName = coinsList[coin].symbol,
                            price = coinsList[coin].currentPrice,
                            priceChange = coinsList[coin].priceChangePercentage24H,
                            usd = selectedCurrency == currencies[0],
                            onClick = {
                                navController.navigate(
                                    Route.CoinDataScreen.withArgs(
                                        coinsList[coin].id
                                    )
                                )
                            }
                        )
                    }
                }
            }
        }
    }

    LaunchedEffect(selectedCurrency) {
        viewModel.updateConnectionState(viewModel.isInternetAvailable(context))
        if (selectedCurrency == currencies[0])
            viewModel.getCoinsList(Currency.USD)
        else
            viewModel.getCoinsList(Currency.RUB)
    }
}