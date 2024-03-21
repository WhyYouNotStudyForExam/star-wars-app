package com.example.starwarschallenge.presentation.character_details

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.starwarschallenge.domain.use_case.StarwarsUseCases
import com.example.starwarschallenge.presentation.character_details.components.CharacterDetailsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailsViewModel @Inject constructor(
    private val starwarsUseCases: StarwarsUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(CharacterDetailsState())
    val state: State<CharacterDetailsState> = _state

    init {
        savedStateHandle.get<Int>("id")?.let { id ->
            viewModelScope.launch {
                starwarsUseCases.getCharacterUseCase(id).also { character ->
                    _state.value = state.value.copy(
                        character = character
                    )
                }
            }
        }
    }
}