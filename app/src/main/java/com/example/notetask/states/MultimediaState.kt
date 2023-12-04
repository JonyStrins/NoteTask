package com.example.notetask.states

import android.net.Uri
import com.example.notetask.models.MultimediaEntity

data class MultimediaState(
    val multimedia: MultimediaEntity = MultimediaEntity(0, "", 0, 0, 0, null, null),
    val multimedias: List<MultimediaEntity> = emptyList()
)
