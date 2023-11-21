package com.example.notetask.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.notetask.models.MultimediaEntity

@Dao
interface MultimediaDao {

    @Query("SELECT * FROM MultimediaEntity WHERE tipo = 1 AND idNota = :idNota ORDER BY id desc")
    fun getMultimediaNotas(idNota: Int): List<MultimediaEntity>

    @Insert
    fun insertarMultimedia(multimedia: MultimediaEntity)

    @Delete
    fun eliminarMultimedia(multimedia: MultimediaEntity)
}