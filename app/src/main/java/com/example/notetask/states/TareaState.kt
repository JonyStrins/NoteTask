package com.example.notetask.states

import com.example.notetask.models.TareaEntity

data class TareaState(
    val tarea: TareaEntity = TareaEntity(id = 0, titulo = null, contenido = null, estatus = null, tipo = null, fecha = null, fechaModi = null, fechaCum = null),
    val tareas: List<TareaEntity> = emptyList()
)
