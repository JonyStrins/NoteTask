package com.example.notetask.states

import com.example.notetask.models.NotaEntity
import java.time.LocalDateTime

data class NotaState(
    val nota: NotaEntity = NotaEntity(id = 0, titulo = null, descripcion = null, multimedia = null, fecha = null, estatus = null, tipo = null, fechaModi = null, fechaCum = null),
    val notas: List<NotaEntity> = emptyList()
)
