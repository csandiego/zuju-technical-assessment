package dev.csandiego.zuju.technicalassessment.service

import dev.csandiego.zuju.technicalassessment.data.Reminder
import kotlinx.coroutines.flow.Flow

interface ReminderService {

    suspend fun insert(reminder: Reminder)

    suspend fun delete(reminder: Reminder)

    fun getAllAsFlow(): Flow<List<Reminder>>
}