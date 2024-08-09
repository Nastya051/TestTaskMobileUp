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
import com.example.testtaskmobileup.R
import java.util.Locale

@Composable
fun CoinInListCard(
    modifier: Modifier = Modifier, imageUrl: String, name: String, shortName: String,
    price: Double, priceChange: Double, usd: Boolean, onClick: () -> Unit
    /*CoinUi*/
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
                    painter = rememberAsyncImagePainter(imageUrl),
                    contentDescription = "Coin",
                    modifier = Modifier
                        .size(70.dp)
                        .padding(start = 16.dp, top = 8.dp, bottom = 8.dp, end = 8.dp)
                )
                Column(verticalArrangement = Arrangement.spacedBy(3.dp)) {
                    Text(
                        text = name,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.W500),
                        color = MaterialTheme.colorScheme.tertiary,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = shortName.uppercase(Locale.ROOT),
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
                    text = if (usd) stringResource(id = R.string.usd, price.toSeparatedNumber())
                    else stringResource(id = R.string.rub, price.toSeparatedNumber()),
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.W600),
                    color = MaterialTheme.colorScheme.tertiary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.End
                )
                Text(
                    text = if (priceChange < 0) stringResource(id = R.string.minus,
                        (priceChange * (-1)).toRoundedUpDouble())
                    else stringResource(id = R.string.plus, priceChange.toRoundedUpDouble()),
                    style = MaterialTheme.typography.bodySmall,
                    color = if (priceChange < 0) MaterialTheme.colorScheme.onSecondary
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
    var resultString = ""

    if (fractionalPart.length < 2) {
        fractionalPart = fractionalPart.plus("0")
    }

    resultString = resultString.plus(integerPart)
    resultString = resultString.plus(".")
    resultString = resultString.plus(fractionalPart)
    return resultString
}

fun Double.toSeparatedNumber(): String {
    val integerPart = this.toInt()
    var fractionalPart = this.toString().substringAfter(".")
    val integerToString = integerPart.toString()
    val array = integerToString.toList()
    val remainder = array.size % 3//кол-во первых цифр до запятой
    var countComma = array.size / 3//кол-во запятых
    var countSymbolsToComma = 0//символов до запятой
    var resultString = ""

    if (fractionalPart.length < 2) {//докидываем нули, если потеряли при переводе в строку
        fractionalPart = fractionalPart.plus("0")
    }

    if (array.size <= 3) {
        return this.toRoundedUpDouble()
    }

    for (i in 0..<remainder) {//записываем первые цифры до запятой
        resultString = resultString.plus(array[i].toString())
    }
    resultString = resultString.plus(",")

    while (countComma > 0) {//записываем по 3 цифры через запятую
        for (rem in remainder..<array.size) {
            resultString = resultString.plus(array[rem].toString())
            countSymbolsToComma++

            if (countSymbolsToComma > 2) {
                if (rem == array.size - 1) {
                    countComma--
                } else {
                    resultString = resultString.plus(",")
                    countSymbolsToComma = 0
                    countComma--
                }
            }
        }
    }
    resultString = resultString.plus(".")
    resultString = resultString.plus(fractionalPart)

    return resultString
}