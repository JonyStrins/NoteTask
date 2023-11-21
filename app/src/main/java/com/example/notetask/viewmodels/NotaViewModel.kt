package com.example.notetask.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.notetask.repository.NotaRepository
import com.example.notetask.states.NotaState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.example.notetask.models.NotaEntity
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

    fun obtenerNotaID(id: Int){
        viewModelScope.launch {
            state = state.copy(
                nota = repository.getByID(id)
            )
        }
    }

    fun obtenerUltimaNota(): Int{
        return repository.getUltimoIdNota()
    }

    fun guardarNota(nota: NotaEntity){
        viewModelScope.launch {
            repository.insertNota(nota)
        }
    }

    fun actualizarNota(nota: NotaEntity){
        viewModelScope.launch {
            repository.actualizarNota(nota)
        }
    }

    fun eliminarNota(nota:NotaEntity){
        viewModelScope.launch {
            repository.eliminarNota(nota)
        }
    }
}