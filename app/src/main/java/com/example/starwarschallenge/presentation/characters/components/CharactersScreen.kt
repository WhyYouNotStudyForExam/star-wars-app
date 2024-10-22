package com.example.starwarschallenge.presentation.characters.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.starwarschallenge.domain.model.Character
import com.example.starwarschallenge.presentation.characters.CharactersViewModel
import com.example.starwarschallenge.presentation.util.Screen
import com.google.gson.Gson
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun CharactersScreen(
    navController: NavController,
    viewModel: CharactersViewModel = hiltViewModel(),
) {
    val state = viewModel.state.value

    when {
        state.isLoading -> {
            LoadingScreen()
        }

        state.characters.isEmpty() && !state.isLoading -> {
            EmptyCharactersScreen()
        }

        else -> {
            CharactersList(
                characters = state.characters,
                navController = navController,
                onLoadMore = { viewModel.loadNextPage() }
            )
        }
    }
}

@Composable
fun CharactersList(
    characters: List<Character>,
    navController: NavController,
    onLoadMore: () -> Unit
) {
    val listState = rememberLazyListState() // Track scroll state

    LazyColumn(
        state = listState, // Use the scroll state
        modifier = Modifier.fillMaxSize()
    ) {
        items(characters) { character ->
            CharacterItem(
                character = character,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        navController.navigate(
                            Screen.CharacterDetailsScreen.route +
                                    "?selectedCharacter=${serializedCharacter(character)}"
                        )
                    }
            )
        }
    }

    // Trigger pagination if scrolled to the bottom
    LaunchedEffect(listState) {
        if (listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index == characters.size - 1) {
            onLoadMore()
        }
    }
}

@Composable
fun LoadingScreen() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator()
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Loading characters...", style = MaterialTheme.typography.bodyMedium)
        }
    }
}

@Composable
fun EmptyCharactersScreen() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "No characters",
                modifier = Modifier.size(64.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "No characters found", style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Please try again later.", style = MaterialTheme.typography.bodyMedium)
        }
    }
}

fun serializedCharacter(character: Character): String {
    return URLEncoder.encode(Gson().toJson(character), StandardCharsets.UTF_8.toString())
}
