package com.example.notetask.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.notetask.models.NotaEntity

@Dao
interface NotaDao {

    @Query("SELECT * FROM NotaEntity ORDER BY id desc")
    fun getNotas(): List<NotaEntity>

    @Query("SELECT * FROM NotaEntity WHERE id = :notaId")
    fun getNotaById(notaId: Int): NotaEntity

    @Query("SELECT MAX(id) FROM NotaEntity")
    fun getUltimoIdNota(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNota(nota: NotaEntity)

    @Update
    fun actualizarNota(nota: NotaEntity)

    @Delete
    fun eliminarNota(nota: NotaEntity)
}