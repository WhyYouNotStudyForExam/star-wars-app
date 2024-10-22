package com.example.starwarschallenge.domain.repository

import com.example.starwarschallenge.domain.model.Character
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("api/all.json")
    suspend fun getCharacters(
        @Query("page") page: Int = 1,
        @Query("limit") limit: Int = 20
    ): List<Character>

    @GET("api/id/{id}.json")
    suspend fun getCharacterById(@Path("id") id: Int): Character?
}