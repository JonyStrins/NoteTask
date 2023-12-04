package com.example.notetask.models

import androidx.room.PrimaryKey

class TareaEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val titulo: String?,
    val contenido: String?,
    val estatus: Int?,
    val tipo: Int?,
    val fecha: String?,
    val fechaModi: String?,
    val fechaCum: String?
)

//estos datos son los que se tienen en la API de visual studio