package com.example.notetask.repository

import com.example.notetask.dao.MultimediaDao
import com.example.notetask.models.MultimediaEntity
import com.example.notetask.models.NotaEntity

class MultimediaRepository(
    private val multiDao: MultimediaDao
) {

    fun getTodaMultimedia(): List<MultimediaEntity>{
        return multiDao.getTodaMultimedia()
    }

    // NOTAS
    fun getMultimediaNota(idNota: Int): List<MultimediaEntity>{
        return multiDao.getMultimediaNotas(idNota)
    }

    fun insertMulti(multi: MultimediaEntity){
        val entity = MultimediaEntity(
            id = 0,
            uri = multi.uri,
            tipo = 1,
            idNota = multi.idNota,
            idTarea = null
        )

        multiDao.insertarMultimedia(entity)
    }

    fun eliminarMultimedia(multi: MultimediaEntity){
        multiDao.eliminarMultimedia(multi)
    }
}