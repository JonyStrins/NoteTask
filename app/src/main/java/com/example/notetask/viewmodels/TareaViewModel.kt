package com.example.notetask.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.notetask.repository.TareaRepository
import com.example.notetask.states.NotaState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import com.example.notetask.models.TareaEntity
import com.example.notetask.states.TareaState

class TareaViewModel (
    private val repository: TareaRepository
): ViewModel(){
    var state by mutableStateOf(TareaState())
        private set

    init {
        viewModelScope.launch {
            state = state.copy(
                tareas = repository.getTareas()
            )
        }
    }

    fun obtenerTareaID(id: Int){
        viewModelScope.launch {
            state = state.copy(
                tarea = repository.getByID(id)
            )
        }
    }

    fun obtenerUltimaTarea(): Int{
        return repository.getUltimoIdTarea()
    }

    fun guardarTarea(tarea: TareaEntity){
        viewModelScope.launch {
            repository.insertTarea(tarea)
        }
    }

    fun actualizarTarea(tarea: TareaEntity){
        viewModelScope.launch {
            repository.actualizarTarea(tarea)
        }
    }

    fun eliminarTarea(tarea: TareaEntity){
        viewModelScope.launch {
            repository.eliminarTarea(tarea)
        }
    }
}