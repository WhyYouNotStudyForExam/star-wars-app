package com.example.starwarschallenge.data.data_source

import com.example.starwarschallenge.domain.repository.ApiService
import com.example.starwarschallenge.domain.repository.StarwarsRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://akabab.github.io/starwars-api/"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}