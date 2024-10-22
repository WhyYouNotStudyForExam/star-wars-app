package com.example.starwarschallenge.domain.repository

import com.example.starwarschallenge.domain.model.Character
import kotlinx.coroutines.flow.Flow

interface StarwarsRepository {
    fun getCharacters(): Flow<Result<List<Character>>>
    suspend fun getCharacterById(id: Int): Character
}