package com.example.testtaskmobileup.presentation.ui.screens

import android.text.Layout.Alignment
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
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
import com.example.testtaskmobileup.presentation.ui.custom_views.FailureColumn
import com.example.testtaskmobileup.presentation.ui.custom_views.GroupChips
import com.example.testtaskmobileup.presentation.ui.custom_views.TextForToolBar

@Composable
fun CoinsListScreen() {
    val currencies = LocalContext.current.resources.getStringArray(R.array.currencies)
    var selected by remember { mutableStateOf(currencies[0]) }

    Column(modifier = Modifier) {
        TextForToolBar(
            text = stringResource(id = R.string.coinsList),
            modifier = Modifier.padding(all = 16.dp)
        )
        GroupChips(
            values = currencies.toList(), selected = selected,
            onClick = { selected = it },
            modifier = Modifier.padding(
                top = 16.dp,
                bottom = 13.dp,
                start = 16.dp,
                end = 16.dp
            )
        )
        HorizontalDivider(thickness = 2.dp, modifier = Modifier.shadow(elevation = 5.dp))

        FailureColumn(modifier = Modifier)
    }
}