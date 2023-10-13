package dev.csandiego.zuju.technicalassessment

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import dev.csandiego.zuju.technicalassessment.worker.ReminderWorker

class ReminderBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        OneTimeWorkRequestBuilder<ReminderWorker>()
            .setInputData(
                workDataOf(
                    EXTRA_REMINDER_ID to intent.getLongExtra(EXTRA_REMINDER_ID, 0)
                )
            )
            .build()
            .let { WorkManager.getInstance(context).enqueue(it) }
    }

    companion object {
        const val EXTRA_REMINDER_ID = "reminderId"
    }
}