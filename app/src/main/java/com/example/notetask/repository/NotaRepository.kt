package com.example.notetask.repository

import com.example.notetask.dao.NotaDao
import com.example.notetask.models.NotaEntity

class NotaRepository(
    private val notaDao: NotaDao
) {
    fun getNotas(): List<NotaEntity> {
        return notaDao.getNotas()
    }

    fun getByID(id: Int): NotaEntity{
        return notaDao.getNotaById(id)
    }

    fun getUltimoIdNota(): Int{
        return notaDao.getUltimoIdNota()
    }

    fun insertNota(nota: NotaEntity){
        val entity = NotaEntity(
            id = 0,
            titulo = nota.titulo,
            descripcion = nota.descripcion,
            multimedia = nota.multimedia,
            fecha = nota.fecha
        )

        notaDao.insertNota(entity)
    }

    fun actualizarNota(nota: NotaEntity){
        val entity = NotaEntity(
            id = nota.id,
            titulo = nota.titulo,
            descripcion = nota.descripcion,
            multimedia = nota.multimedia,
            fecha = nota.fecha
        )

        notaDao.actualizarNota(entity)
    }

    fun eliminarNota(nota: NotaEntity){
        val entity = NotaEntity(
            id = nota.id,
            titulo = nota.titulo,
            descripcion = nota.descripcion,
            multimedia = nota.multimedia,
            fecha = nota.fecha
        )
        notaDao.eliminarNota(entity)
    }
}