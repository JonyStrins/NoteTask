package com.example.notetask.models

import android.net.Uri
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MultimediaEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val uri: String,
    // 1 Para nota
    //2 Para tarea
    val tipo: Int,
    val idNota: Int?,
    val idTarea: Int?
)