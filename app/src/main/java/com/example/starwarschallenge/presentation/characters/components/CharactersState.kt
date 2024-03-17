package com.example.starwarschallenge.presentation.characters.components

import com.example.starwarschallenge.domain.model.Character

data class CharactersState(
    val characters: List<Character> = emptyList()
)