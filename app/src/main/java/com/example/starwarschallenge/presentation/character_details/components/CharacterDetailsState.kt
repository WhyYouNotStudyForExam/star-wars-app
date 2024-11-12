package com.example.starwarschallenge.presentation.character_details.components

import com.example.starwarschallenge.domain.model.Character

data class CharacterDetailsState(
    val character: Character? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)