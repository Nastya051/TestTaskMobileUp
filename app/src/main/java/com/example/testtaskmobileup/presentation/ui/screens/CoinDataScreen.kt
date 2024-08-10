package com.example.testtaskmobileup.presentation.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.domain.models.coins.CurrentCoinDataUi
import com.example.testtaskmobileup.R
import com.example.testtaskmobileup.presentation.enums.ResponseState
import com.example.testtaskmobileup.presentation.navigation.LocalNavController
import com.example.testtaskmobileup.presentation.navigation.Route
import com.example.testtaskmobileup.presentation.ui.custom_views.columns.CircularLoadingAnimationColumn
import com.example.testtaskmobileup.presentation.ui.custom_views.columns.FailureColumn
import com.example.testtaskmobileup.presentation.ui.custom_views.texts.HeaderText
import com.example.testtaskmobileup.viewmodels.CoinsViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun CoinDataScreen(id: String) {
    val navController = LocalNavController.current!!
    val context = LocalContext.current
    val viewModel: CoinsViewModel = getViewModel()
    val state by viewModel.currentState.collectAsState()
    val coinData by viewModel.coinData.collectAsState()

    LaunchedEffect(true) {
        viewModel.updateConnectionState(viewModel.isInternetAvailable(context))
        viewModel.getCoinData(id = id)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.primary),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_arrow_back_24),
                contentDescription = "Arrow back",
                modifier = Modifier
                    .padding(start = 20.dp, top = 16.dp, bottom = 16.dp, end = 32.dp)
                    .clickable { navController.navigate(Route.CoinsListScreen.path) }
            )

            HeaderText(
                text = coinData.name,
                color = MaterialTheme.colorScheme.tertiary.copy(alpha = 0.87f)
            )
        }
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
                    viewModel.getCoinData(id = id)
                })
            }
            ResponseState.SUCCESS -> LazyColumnContent(coinData = coinData)
        }
    }
}

@Composable
private fun DescriptionText(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodyMedium,
        color = MaterialTheme.colorScheme.tertiary,
        textAlign = TextAlign.Start,
        modifier = modifier
    )
}

@Composable
private fun LazyColumnContent(coinData: CurrentCoinDataUi) {
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 34.dp)
    ) {
        item {
            Image(
                painter = rememberAsyncImagePainter(coinData.image),
                contentDescription = "Coin",
                modifier = Modifier
                    .size(120.dp)
                    .padding(top = 10.dp)
            )
        }
        item {
            HeaderText(
                text = stringResource(id = R.string.description),
                color = MaterialTheme.colorScheme.tertiary,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            )
        }
        item {
            DescriptionText(
                text = coinData.description,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
            )
        }
        item {
            HeaderText(
                text = stringResource(id = R.string.categories),
                color = MaterialTheme.colorScheme.tertiary,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            )
        }
        item {
            DescriptionText(
                text = coinData.categories.joinToString(separator = ", "),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
            )
        }
    }
}