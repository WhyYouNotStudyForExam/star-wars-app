package com.example.starwarschallenge.domain.use_case

import com.example.starwarschallenge.domain.model.Character
import com.example.starwarschallenge.domain.repository.StarwarsRepository
import kotlinx.coroutines.flow.first

class LoadNextPageUseCase(
    private val repository: StarwarsRepository
) {
    suspend operator fun invoke():Result<List<Character>> {
        return try {
            repository.getCharacters().first()
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}