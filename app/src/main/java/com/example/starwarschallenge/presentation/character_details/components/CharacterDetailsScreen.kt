package com.example.starwarschallenge.presentation.character_details.components

import CustomDeserializer
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.starwarschallenge.domain.model.Character
import com.example.starwarschallenge.presentation.character_details.CharacterDetailsViewModel
import com.google.gson.GsonBuilder

@Composable
fun CharacterDetailsScreen(
    navController: NavController,
    character: String,
    viewModel: CharacterDetailsViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val characterDetails = deserializeCharacter(character)
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = characterDetails.name)
        Text(text = characterDetails.height.toString())
        Text(text = characterDetails.mass.toString())
        Text(text = characterDetails.gender)
        Text(text = formatList(characterDetails.homeworld))
        Text(text = characterDetails.wiki.toString())
        Text(text = characterDetails.image.toString())
        Text(text = characterDetails.born.toString())
        // Don't show in case of null
        characterDetails.bornLocation?.let { Text(text = it) }
    }
}

fun deserializeCharacter(character: String): Character {
    val gson = GsonBuilder()
        .registerTypeAdapter(Character::class.java, CustomDeserializer())
        .create()

    return gson.fromJson(character, Character::class.java)
}

fun formatList(list: Any?): String {
    return when (list) {
        is List<*> -> list.joinToString(separator = ", ") { it.toString() }
        is String -> list
        else -> ""
    }
}
