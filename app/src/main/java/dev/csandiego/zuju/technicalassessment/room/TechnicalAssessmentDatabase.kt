package dev.csandiego.zuju.technicalassessment.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import dev.csandiego.zuju.technicalassessment.dao.ReminderDao
import dev.csandiego.zuju.technicalassessment.data.Reminder

@Database(entities = [Reminder::class], version = 1, exportSchema = false)
abstract class TechnicalAssessmentDatabase : RoomDatabase() {

    abstract fun reminderDao(): ReminderDao

    companion object {
        private var database: TechnicalAssessmentDatabase? = null

        fun getInstance(context: Context): TechnicalAssessmentDatabase =
            database ?: Room.databaseBuilder(
                context,
                TechnicalAssessmentDatabase::class.java,
                "database.db"
            ).build().also { database = it }

    }
}