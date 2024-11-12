package com.example.starwarschallenge.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.starwarschallenge.presentation.character_details.components.CharacterDetailsScreen
import com.example.starwarschallenge.presentation.characters.components.CharactersScreen
import com.example.starwarschallenge.presentation.util.Screen
import com.example.starwarschallenge.ui.theme.StarWarsChallengeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StarWarsChallengeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.CharactersScreen.route
                    ) {
                        composable(route = Screen.CharactersScreen.route) {
                            CharactersScreen(navController = navController)
                        }
                        composable(route = Screen.CharacterDetailsScreen.route +
                                "?id={id}",
                            arguments = listOf(
                                navArgument("id") {
                                    type = NavType.IntType
                                }
                            )
                        ) {
                            CharacterDetailsScreen(
                                navController = navController
                            )
                        }
                    }
                }
            }
        }
    }
}