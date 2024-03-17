package com.example.starwarschallenge.presentation.characters

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.starwarschallenge.domain.use_case.CharacterUseCases
import com.example.starwarschallenge.presentation.characters.components.CharactersState
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn

class CharactersViewModel(
    private val characterUseCases: CharacterUseCases
) : ViewModel() {

    private val _state = mutableStateOf(CharactersState())
    val state: State<CharactersState> = _state
    private var getCharactersJob: Job? = null

    init {
        getCharacters()
    }

    fun onEvent() {
        getCharacters()
    }

    private fun getCharacters() {
        // TODO: check if there are no multiple flows
        getCharactersJob?.cancel()
        getCharactersJob = characterUseCases.getCharactersUseCase()
            .launchIn(viewModelScope)
    }
}