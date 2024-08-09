package com.example.testtaskmobileup.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.testtaskmobileup.presentation.navigation.MainNavigationController
import com.example.testtaskmobileup.presentation.ui.theme.TestTaskMobileUpTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            TestTaskMobileUpTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    MainNavigationController(navController = navController)
                }
            }
        }
    }
}