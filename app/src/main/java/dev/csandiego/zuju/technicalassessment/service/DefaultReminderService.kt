package dev.csandiego.zuju.technicalassessment.service

import dev.csandiego.zuju.technicalassessment.dao.ReminderDao
import dev.csandiego.zuju.technicalassessment.data.Reminder
import kotlinx.coroutines.flow.Flow

class DefaultReminderService(private val dao: ReminderDao) : ReminderService {

    override suspend fun insert(reminder: Reminder) {
        dao.insert(reminder)
    }

    override suspend fun delete(reminder: Reminder) {
        dao.delete(reminder)
    }

    override fun getAllAsFlow(): Flow<List<Reminder>> = dao.getAllAsFlow()
}