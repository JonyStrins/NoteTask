package com.example.notetask.models

import java.util.Date

data class Nota(
    val titulo: String?,
    val descripcion: String?,
    val multimedia: String?,
    val fecha: Date?
)
