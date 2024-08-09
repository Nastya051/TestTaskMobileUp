package com.example.testtaskmobileup.presentation.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color


private val LightColorScheme = lightColorScheme(
    primary = WhiteFF,
    primaryContainer = Orange00,
    secondary = Yellow25,
    secondaryContainer = Grey9B,
    tertiary = Black00,
    onPrimary = Green8F,
    onSecondary = Red57,
    outline = Color.Transparent
)

@Composable
fun TestTaskMobileUpTheme(
    content: @Composable () -> Unit
) {

    val colorScheme = LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}