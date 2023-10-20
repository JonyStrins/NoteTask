package com.example.notetask

import com.example.notetask.dao.NotaDao
import com.example.notetask.models.Nota
import com.example.notetask.models.NotaEntity

class NotaRepository(
    private val notaDao: NotaDao
) {
    fun getNotas(): List<NotaEntity> {
        return notaDao.getNotas()
    }

    fun getByID(id: Int): Nota{
        val entitie = notaDao.getNotaById(id)

        var newEntity = Nota(
            titulo = entitie.titulo,
            descripcion = entitie.descripcion,
            multimedia = entitie.multimedia,
            fecha = entitie.fecha
        )

        return newEntity
    }

    fun insertNota(nota: Nota){
        val entity = NotaEntity(
            id = 0,
            titulo = nota.titulo,
            descripcion = nota.descripcion,
            multimedia = nota.multimedia,
            fecha = nota.fecha
        )

        notaDao.insertNota(entity)
    }
}