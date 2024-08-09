package com.example.testtaskmobileup.presentation.navigation

sealed class Route(val path: String) {

    data object CoinsListScreen : Route(path = "coins_list_screen")
    data object CoinDataScreen : Route(path = "coin_data_screen")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(path)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}