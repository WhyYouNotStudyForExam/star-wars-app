package com.example.starwarschallenge.presentation.util

sealed class Screen (val route: String)
{
    object CharactersScreen: Screen("characters_screen")
    object CharacterDetailsScreen: Screen("character_details_screen")
}