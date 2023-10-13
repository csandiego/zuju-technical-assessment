package dev.csandiego.zuju.technicalassessment.service

import dev.csandiego.zuju.technicalassessment.dao.ReminderDao
import dev.csandiego.zuju.technicalassessment.data.Reminder
import kotlinx.coroutines.flow.Flow

class DefaultReminderService(private val dao: ReminderDao) : ReminderService {

    override suspend fun insert(reminder: Reminder): Long = dao.insert(reminder)

    override suspend fun delete(reminder: Reminder) {
        dao.delete(reminder)
    }

    override fun flowByDateDescriptionHomeAway(
        date: String,
        description: String,
        home: String,
        away: String
    ): Flow<Reminder?> = dao.flowByDateDescriptionHomeAway(date, description, home, away)

    override suspend fun get(id: Long): Reminder? = dao.get(id)
}