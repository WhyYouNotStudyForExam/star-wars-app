package com.example.starwarschallenge.presentation.characters.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.starwarschallenge.presentation.characters.CharactersViewModel

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
                    .fillMaxSize()
                    .clickable {
                        //TODO: implement routes
                    })
        }
    }
}