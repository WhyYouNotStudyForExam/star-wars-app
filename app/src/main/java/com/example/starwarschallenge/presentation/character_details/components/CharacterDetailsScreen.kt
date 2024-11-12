package com.example.starwarschallenge.presentation.character_details.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.starwarschallenge.domain.model.Character
import com.example.starwarschallenge.presentation.character_details.CharacterDetailsViewModel

@Composable
fun CharacterDetailsScreen(
    navController: NavController,
    viewModel: CharacterDetailsViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    when {
        state.isLoading -> {
            LoadingScreen()
        }

        state.errorMessage != null -> {
            ErrorScreen(errorMessage = state.errorMessage)
        }

        state.character != null -> {
            CharacterDetailsContent(character = state.character)
        }

        else -> {
            Text("No character found.")
        }
    }
}

@Composable
fun LoadingScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun ErrorScreen(errorMessage: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Error: $errorMessage")
    }
}

@Composable
fun CharacterDetailsContent(character: Character) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(5.dp)
    ) {
        Text(text = "Name: ${character.name}", style = MaterialTheme.typography.headlineMedium)
        AsyncImage(
            model = character.image.toString(),
            contentDescription = "Portrait of ${character.name}",
        )
        Text(text = "Height: ${character.height}", style = MaterialTheme.typography.bodyMedium)
        Text(text = "Mass: ${character.mass}", style = MaterialTheme.typography.bodyMedium)
        Text(text = "Gender: ${character.gender}", style = MaterialTheme.typography.bodyMedium)
        Text(
            text = "Homeworld: ${character.homeworld}",
            style = MaterialTheme.typography.bodyMedium
        )
        Text(text = "Wiki: ${character.wiki}", style = MaterialTheme.typography.bodyMedium)
    }
}
