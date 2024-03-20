package com.example.starwarschallenge.presentation.character_details.components

import CustomDeserializer
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
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
    val characterName = characterDetails.name
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(5.dp)
    ) {
        Text(
            text = characterName,
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth(),
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            ),
            textAlign = TextAlign.Center
        )
        Text(text = characterDetails.height.toString())
        Text(text = characterDetails.mass.toString())
        Text(text = characterDetails.gender)
        Text(text = formatList(characterDetails.homeworld))
        Text(text = characterDetails.wiki.toString())
        AsyncImage(
            model = characterDetails.image.toString(),
            contentDescription = "Portrait of $characterName",
        )
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
