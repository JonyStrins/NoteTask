package com.example.notetask.states

import com.example.notetask.models.NotaEntity

data class NotaState(
    val nota: NotaEntity = NotaEntity(id = 0, titulo = null, descripcion = null, multimedia = null, fecha = null),
    val notas: List<NotaEntity> = emptyList()
)
