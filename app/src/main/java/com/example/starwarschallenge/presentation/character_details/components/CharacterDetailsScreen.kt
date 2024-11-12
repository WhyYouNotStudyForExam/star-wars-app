package com.example.starwarschallenge.presentation.character_details.components

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
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
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        item {
            Card(
                shape = RoundedCornerShape(8.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(16.dp)
                ) {
                    AsyncImage(
                        model = character.image.toString(),
                        contentDescription = "Portrait of ${character.name}",
                        modifier = Modifier
                            .height(250.dp)
                            .fillMaxWidth()
                            .border(
                                2.dp,
                                MaterialTheme.colorScheme.primary,
                                shape = RoundedCornerShape(8.dp)
                            )
                            .background(
                                MaterialTheme.colorScheme.onBackground,
                                shape = RoundedCornerShape(8.dp)
                            )
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = character.name,
                        style = MaterialTheme.typography.headlineMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }

        item {
            Section(
                title = "Physical Attributes"
            ) {
                CharacterAttribute(label = "Height", value = "${character.height ?: "Unknown"} cm")
                CharacterAttribute(label = "Mass", value = "${character.mass ?: "Unknown"} kg")
                CharacterAttribute(label = "Gender", value = character.gender.ifEmpty { "Unknown" })
            }
        }

        item {
            Section(title = "Personal Details") {
                CharacterAttribute(
                    label = "Homeworld",
                    value = character.homeworld?.toString() ?: "Unknown"
                )
                CharacterAttribute(label = "Born", value = character.born ?: "Unknown")
                CharacterAttribute(
                    label = "Born Location",
                    value = character.bornLocation ?: "Unknown"
                )
                CharacterAttribute(label = "Died", value = character.died?.toString() ?: "Unknown")
                CharacterAttribute(
                    label = "Died Location",
                    value = character.diedLocation ?: "Unknown"
                )
                CharacterAttribute(label = "Species", value = character.species ?: "Unknown")
                character.wiki?.let {
                    CharacterAttribute(
                        label = "Wiki",
                        value = it,
                        isLink = true
                    )
                }
            }
        }

        item {
            Section(title = "Appearance") {
                CharacterAttribute(label = "Hair Color", value = character.hairColor ?: "Unknown")
                CharacterAttribute(label = "Eye Color", value = character.eyeColor ?: "Unknown")
                CharacterAttribute(label = "Skin Color", value = character.skinColor ?: "Unknown")
                CharacterAttribute(
                    label = "Cybernetics",
                    value = character.cybernetics?.joinToString() ?: "None"
                )
            }
        }

        item {
            Section(title = "Affiliations and Relationships") {
                CharacterAttribute(
                    label = "Affiliations",
                    value = character.affiliations?.joinToString() ?: "None"
                )
                CharacterAttribute(
                    label = "Former Affiliations",
                    value = character.formerAffiliations?.joinToString() ?: "None"
                )
                CharacterAttribute(
                    label = "Masters",
                    value = character.masters?.joinToString() ?: "None"
                )
                CharacterAttribute(
                    label = "Apprentices",
                    value = character.apprentices?.joinToString() ?: "None"
                )
            }

        }

        item {
            Section(title = "Creation and Manufacturing") {
                CharacterAttribute(
                    label = "Date Created",
                    value = character.dateCreated?.toString() ?: "Unknown"
                )
                CharacterAttribute(
                    label = "Date Destroyed",
                    value = character.dateDestroyed?.toString() ?: "Unknown"
                )
                CharacterAttribute(
                    label = "Destroyed Location",
                    value = character.destroyedLocation ?: "Unknown"
                )
                CharacterAttribute(label = "Creator", value = character.creator ?: "Unknown")
                CharacterAttribute(
                    label = "Manufacturer",
                    value = character.manufacturer ?: "Unknown"
                )
                CharacterAttribute(label = "Model", value = character.model ?: "Unknown")
                CharacterAttribute(label = "Class", value = character.classType ?: "Unknown")
                CharacterAttribute(
                    label = "Sensor Color",
                    value = character.sensorColor ?: "Unknown"
                )
                CharacterAttribute(
                    label = "Plating Color",
                    value = character.platingColor ?: "Unknown"
                )
            }
        }

        // Equipment Section
        item {
            Section(title = "Equipment") {
                CharacterAttribute(
                    label = "Equipment",
                    value = character.equipment?.joinToString() ?: "None"
                )
            }
        }
    }
}

@Composable
fun CharacterAttribute(label: String, value: String, isLink: Boolean = false) {
    val context = LocalContext.current
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(enabled = isLink) {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(value))
                context.startActivity(intent)
            },
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = "$label:", style = MaterialTheme.typography.bodyMedium, color = Color.Gray)
        Text(text = value, style = MaterialTheme.typography.bodyMedium)
    }
}

@Composable
fun Section(title: String, content: @Composable ColumnScope.() -> Unit) {
    Text(
        text = title,
        style = MaterialTheme.typography.headlineSmall,
        modifier = Modifier.padding(vertical = 8.dp)
    )
    Divider()
    Column(verticalArrangement = Arrangement.spacedBy(8.dp), content = content)
}