package com.example.notetask.models

import androidx.room.PrimaryKey

class TareaEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val titulo: String?,
    val contenido: String?,
    val multimedia: String?,
    val fecha: String?
    //se hizo la entity
)

