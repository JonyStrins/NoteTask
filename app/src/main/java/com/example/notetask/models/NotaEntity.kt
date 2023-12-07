package com.example.notetask.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class NotaEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val titulo: String?,
    val descripcion: String ?,
    val multimedia: String?,
    val estatus: Int?,
    val tipo: Int?,
    val fecha: String,
    val fechaModi: String?,
    val fechaCum: String?,
)


