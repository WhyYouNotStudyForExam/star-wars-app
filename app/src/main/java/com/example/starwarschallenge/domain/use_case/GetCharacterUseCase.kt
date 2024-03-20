package com.example.starwarschallenge.domain.use_case

import com.example.starwarschallenge.domain.repository.StarwarsRepository

class GetCharacterUseCase (
    private val repository: StarwarsRepository
) {
    operator fun invoke(id: Int) {
        return repository.getCharacterById(id)
    }
}