package com.example.notetask.states

import com.example.notetask.models.Nota
import com.example.notetask.models.NotaEntity

data class NotaState(
    val nota: Nota = Nota(titulo = null, descripcion = null, multimedia = null, fecha = null),
    val notas: List<NotaEntity> = emptyList()
)
