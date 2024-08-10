package com.example.testtaskmobileup.presentation.ui.custom_views.cards

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.domain.models.coins.CoinInMarketUi
import com.example.testtaskmobileup.R
import java.util.Locale

@Composable
fun CoinInListCard(
    modifier: Modifier = Modifier,
    coin: CoinInMarketUi,
    usd: Boolean,
    onClick: () -> Unit

) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RectangleShape,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary),
        onClick = { onClick() }
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = rememberAsyncImagePainter(coin.image),
                    contentDescription = "Coin",
                    modifier = Modifier
                        .size(70.dp)
                        .padding(start = 16.dp, top = 8.dp, bottom = 8.dp, end = 8.dp)
                )
                Column(verticalArrangement = Arrangement.spacedBy(3.dp)) {
                    Text(
                        text = coin.name,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.W500
                        ),
                        color = MaterialTheme.colorScheme.tertiary,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = coin.symbol.uppercase(Locale.ROOT),
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.secondaryContainer,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }

            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp),
                horizontalAlignment = Alignment.End,
                modifier = Modifier.padding(top = 8.dp, bottom = 8.dp, end = 16.dp)
            ) {
                Text(
                    text = if (usd) stringResource(
                        id = R.string.usd,
                        coin.currentPrice.toSeparatedNumber()
                    )
                    else stringResource(id = R.string.rub, coin.currentPrice.toSeparatedNumber()),
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.W600),
                    color = MaterialTheme.colorScheme.tertiary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.End
                )
                Text(
                    text = if (coin.priceChangePercentage24H < 0) stringResource(
                        id = R.string.minus,
                        (coin.priceChangePercentage24H * (-1)).toRoundedUpDouble()
                    )
                    else stringResource(
                        id = R.string.plus,
                        coin.priceChangePercentage24H.toRoundedUpDouble()
                    ),
                    style = MaterialTheme.typography.bodySmall,
                    color = if (coin.priceChangePercentage24H < 0) MaterialTheme.colorScheme.onSecondary
                    else MaterialTheme.colorScheme.onPrimary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.End
                )
            }
        }

    }
}

fun Double.toRoundedUpDouble(): String {
    val integerPart = this.toInt()
    var fractionalPart = this.toString().substringAfter(".")

    if (fractionalPart.length < 2) {
        fractionalPart = fractionalPart.plus("0")
    }

    return "$integerPart.${fractionalPart.subSequence(0, 2)}"
}

fun Double.toSeparatedNumber(): String {
    val integerPart = this.toInt().toString()
    var fractionalPart = this.toString().substringAfter(".")
    var remainder = integerPart.length % 3//кол-во первых цифр до запятой
    var countComma = integerPart.length / 3//кол-во запятых
    var resultString = ""

    if (fractionalPart.length < 2) {//докидываем нули, если потеряли при переводе в строку
        fractionalPart = fractionalPart.plus("0")
    }

    if (integerPart.length <= 3) {
        return this.toRoundedUpDouble()
    }

    if (remainder != 0)
        resultString = "${integerPart.subSequence(0, remainder)},"

    while (countComma > 0) {//записываем по 3 цифры через запятую
        resultString += "${integerPart.subSequence(remainder, remainder + 3)}"
        if (countComma > 1)
            resultString += ","
        remainder += 3
        countComma--
    }
    resultString += ".$fractionalPart"

    return resultString
}