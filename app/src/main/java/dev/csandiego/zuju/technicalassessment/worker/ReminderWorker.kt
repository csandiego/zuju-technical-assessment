package dev.csandiego.zuju.technicalassessment.worker

import android.Manifest
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.content.pm.PackageManager
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import dev.csandiego.zuju.technicalassessment.MainActivity
import dev.csandiego.zuju.technicalassessment.R
import dev.csandiego.zuju.technicalassessment.ReminderBroadcastReceiver.Companion.EXTRA_REMINDER_ID
import dev.csandiego.zuju.technicalassessment.room.TechnicalAssessmentDatabase
import dev.csandiego.zuju.technicalassessment.service.DefaultReminderService

class ReminderWorker(context: Context, params: WorkerParameters) :
    CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        val database = TechnicalAssessmentDatabase.getInstance(applicationContext)
        val service = DefaultReminderService(database.reminderDao())
        service.get(inputData.getLong(EXTRA_REMINDER_ID, 0))?.let { reminder ->
            service.delete(reminder)
            if (ContextCompat.checkSelfPermission(
                    applicationContext,
                    Manifest.permission.POST_NOTIFICATIONS
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                Intent(applicationContext, MainActivity::class.java).apply {
                    flags = FLAG_ACTIVITY_NEW_TASK or FLAG_ACTIVITY_CLEAR_TOP
                }.let {
                    PendingIntent.getActivity(
                        applicationContext, 0, it,
                        PendingIntent.FLAG_IMMUTABLE
                    )
                }.let {
                    NotificationCompat.Builder(applicationContext, "Default")
                        .setSmallIcon(R.drawable.ic_launcher_foreground)
                        .setContentTitle(reminder.description)
                        .setContentText(reminder.description)
                        .setPriority(NotificationCompat.PRIORITY_HIGH)
                        .setContentIntent(it)
                        .setAutoCancel(true)
                        .build()
                }.let {
                    NotificationManagerCompat.from(applicationContext).notify(0, it)
                }
            }
        }
        return Result.success()
    }
}