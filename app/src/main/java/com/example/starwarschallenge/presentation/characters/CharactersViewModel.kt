package com.example.starwarschallenge.presentation.characters

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.starwarschallenge.domain.use_case.LoadNextPageUseCase
import com.example.starwarschallenge.presentation.characters.components.CharactersState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val loadNextPageUseCase: LoadNextPageUseCase
) : ViewModel() {

    private val _state = mutableStateOf(CharactersState())
    val state: State<CharactersState> = _state
    private var getCharactersJob: Job? = null

    init {
        loadNextPage()
    }

    fun loadNextPage() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            val result = loadNextPageUseCase()
            result.fold(
                onSuccess = { newCharacters ->
                    _state.value = _state.value.copy(
                        characters = _state.value.characters + newCharacters,
                        isLoading = false
                    )
                },
                onFailure = { e ->
                    Log.e("CharactersViewModel", "Error fetching characters: ${e.message}")
                    _state.value = _state.value.copy(
                        isLoading = false,
                        errorMessage = e.message
                    )
                }
            )
        }
    }

    override fun onCleared() {
        super.onCleared()
        getCharactersJob?.cancel()
    }
}