package dev.csandiego.zuju.technicalassessment.room

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.csandiego.zuju.technicalassessment.dao.ReminderDao
import dev.csandiego.zuju.technicalassessment.data.Reminder

@Database(entities = [Reminder::class], version = 1, exportSchema = false)
abstract class TechnicalAssessmentDatabase : RoomDatabase() {

    abstract fun reminderDao(): ReminderDao
}