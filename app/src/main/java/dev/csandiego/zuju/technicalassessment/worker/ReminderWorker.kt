package dev.csandiego.zuju.technicalassessment.worker

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import dev.csandiego.zuju.technicalassessment.ReminderBroadcastReceiver.Companion.EXTRA_REMINDER_ID
import dev.csandiego.zuju.technicalassessment.room.TechnicalAssessmentDatabase
import dev.csandiego.zuju.technicalassessment.service.DefaultReminderService

class ReminderWorker(context: Context, params: WorkerParameters) :
    CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        val database = TechnicalAssessmentDatabase.getInstance(applicationContext)
        val service = DefaultReminderService(database.reminderDao())
        service.get(inputData.getLong(EXTRA_REMINDER_ID, 0))?.let {
            Log.d("ReminderWorker", it.toString())
            service.delete(it)
        }
        return Result.success()
    }
}