package com.example.testtaskmobileup.presentation.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.testtaskmobileup.presentation.ui.screens.CoinDataScreen
import com.example.testtaskmobileup.presentation.ui.screens.CoinsListScreen

@Composable
fun MainNavigationController(navController: NavHostController) {
    CompositionLocalProvider(LocalNavController provides navController) {
        NavHost(
            navController = navController,
            startDestination = Route.CoinsListScreen.path,
            enterTransition = { fadeIn(animationSpec = tween(350)) },
            exitTransition = { fadeOut(animationSpec = tween(350)) },
        ) {
            composable(route = Route.CoinsListScreen.path) {
                CoinsListScreen()
            }

            composable(
                route = Route.CoinDataScreen.path + "/{id}",
                arguments = listOf(navArgument("id") {
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = false
                })
            ) { entry ->
                entry.arguments?.getString("id").let { id ->
                    if (id != null) {
                        CoinDataScreen(id = id)
                    }
                }

            }
        }
    }
}