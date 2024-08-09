package com.example.testtaskmobileup.presentation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.testtaskmobileup.R
import com.example.testtaskmobileup.presentation.ui.custom_views.columns.CircularLoadingAnimationColumn
import com.example.testtaskmobileup.presentation.ui.custom_views.chips.GroupChips
import com.example.testtaskmobileup.presentation.ui.custom_views.texts.TextForToolBar

@Composable
fun CoinsListScreen() {
    val currencies = LocalContext.current.resources.getStringArray(R.array.currencies)
    var selected by remember { mutableStateOf(currencies[0]) }

    Column(modifier = Modifier.background(color = MaterialTheme.colorScheme.primary)) {
        TextForToolBar(
            text = stringResource(id = R.string.coinsList),
            modifier = Modifier.padding(all = 16.dp)
        )
        GroupChips(
            values = currencies.toList(),
            selected = selected,
            onClick = { selected = it },
            modifier = Modifier.padding(top = 16.dp, bottom = 13.dp, start = 16.dp, end = 16.dp)
        )
        HorizontalDivider(
            thickness = 1.dp,
            color = MaterialTheme.colorScheme.tertiary.copy(alpha = 0.2f),
            modifier = Modifier.shadow(elevation = 3.dp)
        )

        //в случае ошибки
//        FailureColumn(onClickButton = {/*запрос списка коинов*/ })

        //load
        CircularLoadingAnimationColumn()

        //в случае успеха
//        LazyColumn(
//            modifier = Modifier
//                .padding(top = 8.dp)
//                .background(color = MaterialTheme.colorScheme.primary)
//                .fillMaxSize()
//        ) {
//            items(3) {
//                CoinInListCard(
//                    imageUrl = "https://coin-images.coingecko.com/coins/images/25751/large/kaspa-icon-exchanges.png?1696524837",
//                    name = "Bitcoin",
//                    shortName = "btc",
//                    price = 5001.21,
//                    priceChange = -2.03,
//                    usd = true,
//                    onClick = {/*show current coin*/ }
//                )
//            }
//        }
    }
}