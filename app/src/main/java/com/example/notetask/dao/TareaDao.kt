package com.example.notetask.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.notetask.models.TareaEntity
@Dao
interface TareaDao {
    @Query("SELECT * FROM TareaEntity ORDER BY id desc")
    fun getTareas(): List<TareaEntity>

    @Query("SELECT * FROM TareaEntity WHERE id = :tareaId")
    fun getTareasById(tareaId: Int): TareaEntity

    @Query("SELECT MAX(id) FROM TareaEntity")
    fun getUltimoIdTarea(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTarea(tarea: TareaEntity)

    @Update
    fun actualizarTarea(tarea: TareaEntity)

    @Delete
    fun eliminarTarea(tarea: TareaEntity)
}