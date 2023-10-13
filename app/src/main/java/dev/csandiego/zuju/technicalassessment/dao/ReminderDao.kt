package dev.csandiego.zuju.technicalassessment.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.csandiego.zuju.technicalassessment.data.Reminder
import kotlinx.coroutines.flow.Flow

@Dao
interface ReminderDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(reminder: Reminder): Long

    @Delete
    suspend fun delete(reminder: Reminder)

    @Query("SELECT * FROM reminder WHERE date = :date AND description = :description AND home = :home AND away = :away")
    fun flowByDateDescriptionHomeAway(
        date: String,
        description: String,
        home: String,
        away: String
    ): Flow<Reminder?>

    @Query("SELECT * FROM reminder WHERE id = :id")
    suspend fun get(id: Long): Reminder?
}