package com.example.multiscreenapp

import DetailsScreen
import HomeScreen
import com.example.multiscreenapp.SettingsScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.multiscreenapp.ui.theme.MultiScreenAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            var isDarkMode by remember { mutableStateOf(false) }

            MultiScreenAppTheme(darkTheme = isDarkMode) {
                AppNavigation(isDarkMode) { newMode ->
                    isDarkMode = newMode
                }
            }
        }
    }
}

@Composable
fun AppNavigation(isDarkMode: Boolean, onToggleDarkMode: (Boolean) -> Unit) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(navController, isDarkMode, onToggleDarkMode)
        }
        composable("details/{itemId}") { backStackEntry ->
            val itemId = backStackEntry.arguments?.getString("itemId")
            DetailsScreen(itemId, navController)
        }
        composable("settings") {
            SettingsScreen(isDarkMode, onToggleDarkMode, navController)
        }
    }
}
