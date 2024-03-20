package com.example.starwarschallenge.presentation.characters.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.starwarschallenge.domain.model.Character
import com.example.starwarschallenge.presentation.characters.CharactersViewModel
import com.example.starwarschallenge.presentation.util.Screen
import com.google.gson.Gson

@Composable
fun CharactersScreen(
    navController: NavController,
    viewModel: CharactersViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(state.characters) { character ->
            CharacterItem(character = character,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        navController.navigate(
                            Screen.CharacterDetailsScreen.route +
                            "?selectedCharacter=${serializedCharacter(character)}"
                        )
                    })
        }
    }
}

fun serializedCharacter(character: Character): String {
    return Gson().toJson(character)
}
