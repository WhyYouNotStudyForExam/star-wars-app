package com.example.starwarschallenge.presentation.characters

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.starwarschallenge.domain.use_case.StarwarsUseCases
import com.example.starwarschallenge.presentation.characters.components.CharactersState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val starwarsUseCases: StarwarsUseCases
) : ViewModel() {

    private val _state = mutableStateOf(CharactersState())
    val state: State<CharactersState> = _state
    private var getCharactersJob: Job? = null

    init {
        getCharacters()
    }

    private fun getCharacters() {
        getCharactersJob = starwarsUseCases.getCharactersUseCase()
            .onEach { characters ->
                _state.value = state.value.copy(
                    characters = characters
                )
            }
            .launchIn(viewModelScope)
    }

    override fun onCleared() {
        super.onCleared()
        getCharactersJob?.cancel()
    }
}