package com.example.starwarschallenge.data.data_source

import CustomDeserializer
import com.example.starwarschallenge.domain.model.Character
import com.example.starwarschallenge.domain.repository.ApiService
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://akabab.github.io/starwars-api/"

    val gson = GsonBuilder()
        .registerTypeAdapter(Character::class.java, CustomDeserializer())
        .create()

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    val apiService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}