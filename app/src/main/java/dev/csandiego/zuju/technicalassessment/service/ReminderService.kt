package dev.csandiego.zuju.technicalassessment.service

import dev.csandiego.zuju.technicalassessment.data.Reminder
import kotlinx.coroutines.flow.Flow

interface ReminderService {

    suspend fun insert(reminder: Reminder): Long

    suspend fun delete(reminder: Reminder)

    fun flowByDateDescriptionHomeAway(
        date: String,
        description: String,
        home: String,
        away: String
    ): Flow<Reminder?>

    suspend fun get(id: Long): Reminder?
}