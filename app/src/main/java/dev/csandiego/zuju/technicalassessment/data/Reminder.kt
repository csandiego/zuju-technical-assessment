package dev.csandiego.zuju.technicalassessment.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Reminder(
    @PrimaryKey
    val id: Long,
    val date: String,
    val description: String,
    val home: String,
    val away: String,
)
