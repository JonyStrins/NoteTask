package com.example.notetask.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.notetask.NotaRepository
import com.example.notetask.models.Nota
import com.example.notetask.states.NotaState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class NotaViewModel(
    private val repository: NotaRepository
): ViewModel() {
    var state by mutableStateOf(NotaState())
        private set

    init {
        viewModelScope.launch {
            state = state.copy(
                notas = repository.getNotas()
            )
        }
    }

    fun onNotaChange(nota: Nota){
        state = state.copy(
            nota = nota
        )
    }

    fun obtenerNotaID(id: Int){
        viewModelScope.launch {
            state = state.copy(
                nota = repository.getByID(id)
            )
        }
    }

    fun guardarNota(nota: Nota){
        viewModelScope.launch {
            repository.insertNota(nota)
        }
    }
}