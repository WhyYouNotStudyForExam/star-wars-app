package com.example.starwarschallenge.di

import com.example.starwarschallenge.data.data_source.RetrofitClient.apiService
import com.example.starwarschallenge.data.repository.StarwarsRepositoryImpl
import com.example.starwarschallenge.domain.repository.StarwarsRepository
import com.example.starwarschallenge.domain.use_case.GetCharacterUseCase
import com.example.starwarschallenge.domain.use_case.GetCharactersUseCase
import com.example.starwarschallenge.domain.use_case.LoadNextPageUseCase
import com.example.starwarschallenge.domain.use_case.StarwarsUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideStarwarsRepository(): StarwarsRepository {
        return StarwarsRepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideCharactersUseCases(repository: StarwarsRepository): StarwarsUseCases {
        return StarwarsUseCases(
            getCharactersUseCase = GetCharactersUseCase(repository),
            getCharacterUseCase = GetCharacterUseCase(repository)
        )
    }

    @Provides
    fun provideLoadNextPageUseCase(
        repository: StarwarsRepository
    ): LoadNextPageUseCase {
        return LoadNextPageUseCase(repository)
    }
}