package com.example.starwarschallenge.data.repository

import android.util.Log
import com.example.starwarschallenge.domain.model.Character
import com.example.starwarschallenge.domain.repository.ApiService
import com.example.starwarschallenge.domain.repository.StarwarsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class StarwarsRepositoryImpl(
    private val apiService: ApiService
) : StarwarsRepository {
    private var currentPage = 1

    override fun getCharacters(): Flow<Result<List<Character>>> = flow {
        val characters = apiService.getCharacters(page = currentPage)
        Log.d("StarwarsRepository", "Fetched characters: ${characters.size}")
        emit(Result.success(characters))
        currentPage++
    }.catch { e ->
        Log.e("StarwarsRepository", "Error fetching characters: ${e.message}")
        emit(Result.failure(e))
    }

    override suspend fun getCharacterById(id: Int): Character {
        return try {
            val character = apiService.getCharacterById(id)
            character ?: throw Exception("Character not found")
        } catch (e: Exception) {
            throw e
        }
    }
}