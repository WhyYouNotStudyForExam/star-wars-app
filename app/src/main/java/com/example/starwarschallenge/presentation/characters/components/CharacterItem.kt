package com.example.starwarschallenge.presentation.characters.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.starwarschallenge.domain.model.Character

@Composable
fun CharacterItem(
    character: Character,
    modifier: Modifier
) {
    Box(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(14.dp)
                .padding(end = 16.dp)
        ) {
            Text(text = character.name)
            Text(text = character.gender)
            character.bornLocation?.let { Text(text = it) }
        }
    }
}