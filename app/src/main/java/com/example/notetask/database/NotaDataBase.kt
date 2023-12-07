package com.example.notetask.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.notetask.converters.Converters
import com.example.notetask.dao.MultimediaDao
import com.example.notetask.dao.NotaDao
import com.example.notetask.dao.TareaDao
import com.example.notetask.models.MultimediaEntity
import com.example.notetask.models.NotaEntity
import com.example.notetask.models.TareaEntity


@TypeConverters(Converters::class)
@Database(entities = [NotaEntity::class, MultimediaEntity::class, TareaEntity::class], version = 1)
abstract class NotaDataBase : RoomDatabase() {
    abstract val dao: NotaDao
    abstract val multiDao: MultimediaDao
    abstract val tDao: TareaDao
}