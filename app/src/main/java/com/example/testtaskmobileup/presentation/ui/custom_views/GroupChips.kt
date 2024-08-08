package com.example.testtaskmobileup.presentation.ui.custom_views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp


@Composable
fun GroupChips(
    modifier: Modifier = Modifier,
    values: List<String>,
    selected: String,
    onClick: (String) -> Unit
) {
    Row(modifier = modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        values.forEach { it ->
            CurrencyFilterChip(
                selected = selected,
                text = it,
                onSelected = {
                    onClick(it)
                }
            )
        }
    }
}

@Composable
private fun CurrencyFilterChip(selected: String, text: String, onSelected: (String) -> Unit) {
    FilterChip(
        selected = selected == text,
        onClick = { onSelected(text) },
        label = {
            Text(
                text = text,
                style = MaterialTheme.typography.labelSmall,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        },
        shape = RoundedCornerShape(60.dp),
        colors = FilterChipDefaults.filterChipColors(
            containerColor = MaterialTheme.colorScheme.tertiary.copy(alpha = 0.12f),
            labelColor = MaterialTheme.colorScheme.tertiary.copy(alpha = 0.87f),
            selectedContainerColor = MaterialTheme.colorScheme.secondary.copy(alpha = 0.15f),
            selectedLabelColor = MaterialTheme.colorScheme.secondary
        ),
        modifier = Modifier
            .heightIn(32.dp)
            .width(89.dp)
    )
}