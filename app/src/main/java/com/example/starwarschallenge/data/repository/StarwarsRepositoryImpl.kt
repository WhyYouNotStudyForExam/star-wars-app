package com.example.starwarschallenge.data.repository

import com.example.starwarschallenge.data.data_source.RetrofitClient.apiService
import com.example.starwarschallenge.domain.model.Character
import com.example.starwarschallenge.domain.repository.StarwarsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class StarwarsRepositoryImpl(

) : StarwarsRepository {

    override fun getCharacters(): Flow<List<Character>> = flow {
        emit(apiService.getCharacters())
    }.flowOn(Dispatchers.IO)

    override fun getCharacterById(id: Any): Character {
        TODO("Not yet implemented")
    }
}