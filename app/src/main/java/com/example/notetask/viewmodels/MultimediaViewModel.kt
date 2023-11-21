package com.example.notetask.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notetask.models.MultimediaEntity
import com.example.notetask.repository.MultimediaRepository
import com.example.notetask.states.MultimediaState
import kotlinx.coroutines.launch

class MultimediaViewModel(
    private val repository: MultimediaRepository,
    val idNotas: Int
): ViewModel() {
    var state  by mutableStateOf(MultimediaState())
        private set

    init{
        viewModelScope.launch {
            state = state.copy(
                multimedias = repository.getMultimediaNota(idNotas)
            )
        }
    }

    fun agregarMultimedia(multi: MultimediaEntity){
        viewModelScope.launch {
            repository.insertMulti(multi)
        }
    }

    fun eliminarMultimedia(multi: MultimediaEntity){
        viewModelScope.launch {
            repository.eliminarMultimedia(multi)
        }
    }
}