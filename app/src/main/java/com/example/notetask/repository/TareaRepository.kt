package com.example.notetask.repository

import com.example.notetask.dao.TareaDao
import com.example.notetask.models.TareaEntity
class TareaRepository (
    private val tareaDao: TareaDao
){
    fun getTareas(): List<TareaEntity>{
        return tareaDao.getTareas()
    }

    fun getByID(id: Int): TareaEntity{
        return tareaDao.getTareasById(id)
    }

    fun getUltimoIdTarea(): Int{
        return tareaDao.getUltimoIdTarea()
    }

    fun insertTarea(tarea: TareaEntity){
        val entity = TareaEntity(
            id = 0,
            titulo = tarea.titulo,
            contenido = tarea.contenido,
            estatus = null,
            tipo = null,
            fecha = tarea.fecha,
            fechaModi = null,
            fechaCum = null
        )
        tareaDao.insertTarea(entity)
    }

    fun actualizarTarea(tarea: TareaEntity){
        val entity = TareaEntity(
            id = 0,
            titulo = tarea.titulo,
            contenido = tarea.contenido,
            estatus = null,
            tipo = null,
            fecha = tarea.fecha,
            fechaModi = null,
            fechaCum = null
        )
        tareaDao.actualizarTarea(entity)
    }

    fun eliminarTarea(tarea: TareaEntity){
        val entity = TareaEntity(
            id = 0,
            titulo = tarea.titulo,
            contenido = tarea.contenido,
            estatus = null,
            tipo = null,
            fecha = tarea.fecha,
            fechaModi = null,
            fechaCum = null
        )
        tareaDao.eliminarTarea(entity)
    }
}