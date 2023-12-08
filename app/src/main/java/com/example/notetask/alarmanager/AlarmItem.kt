package com.example.notetask.alarmanager

import java.time.LocalDateTime

data class AlarmItem(
    val time: LocalDateTime,
    val title: String?,
    val message: String?
)
