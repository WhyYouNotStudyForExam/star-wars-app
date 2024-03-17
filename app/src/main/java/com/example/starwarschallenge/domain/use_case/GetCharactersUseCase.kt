package com.example.starwarschallenge.domain.use_case

import com.example.starwarschallenge.domain.model.Character
import com.example.starwarschallenge.domain.repository.StarwarsRepository
import kotlinx.coroutines.flow.Flow

class GetCharactersUseCase (
    private val repository: StarwarsRepository
){
    operator fun invoke(): Flow<List<Character>> {
        return repository.getCharacters()
    }
}