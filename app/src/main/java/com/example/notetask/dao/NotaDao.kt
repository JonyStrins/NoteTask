package com.example.notetask.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.notetask.models.NotaEntity

@Dao
interface NotaDao {

    @Query("SELECT * FROM NotaEntity")
    fun getNotas(): List<NotaEntity>

    @Query("SELECT * FROM NotaEntity WHERE id = :notaId")
    fun getNotaById(notaId: Int): NotaEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNota(nota: NotaEntity)

    @Delete
    fun eliminarNota(nota: NotaEntity)
}