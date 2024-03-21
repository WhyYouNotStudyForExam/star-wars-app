package com.example.starwarschallenge.presentation.util

sealed class Screen(val route: String) {
    data object CharactersScreen : Screen("characters_screen")
    data object CharacterDetailsScreen : Screen("character_details_screen")
}