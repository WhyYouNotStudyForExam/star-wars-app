package com.example.starwarschallenge.domain.repository

import com.example.starwarschallenge.domain.model.Character
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("api/all.json")
    suspend fun getCharacters(): List<Character>

    @GET("api/id/{id}.json")
    suspend fun getCharacterById(@Path("id") id: Int): Character?
}