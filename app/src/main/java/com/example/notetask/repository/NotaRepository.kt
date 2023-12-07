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
            fecha = nota.fecha,
            estatus = null,
            tipo = null,
            fechaModi = null,
            fechaCum = null
        )

        notaDao.insertNota(entity)
    }

    fun actualizarNota(nota: NotaEntity){
        notaDao.actualizarNota(nota)
    }

    fun eliminarNota(nota: NotaEntity){
        notaDao.eliminarNota(nota)
    }
}