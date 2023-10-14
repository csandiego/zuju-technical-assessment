package dev.csandiego.zuju.technicalassessment.compose

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import dev.csandiego.zuju.technicalassessment.ReminderBroadcastReceiver
import dev.csandiego.zuju.technicalassessment.ReminderBroadcastReceiver.Companion.EXTRA_REMINDER_ID
import dev.csandiego.zuju.technicalassessment.data.Match
import dev.csandiego.zuju.technicalassessment.data.Reminder
import dev.csandiego.zuju.technicalassessment.service.ReminderService
import kotlinx.coroutines.launch
import java.util.Date

@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MatchDetailScreen(navController: NavHostController, service: ReminderService, match: Match) {
    val context = LocalContext.current
    val reminder by service.flowByDateDescriptionHomeAway(
        match.date,
        match.description,
        match.home,
        match.away
    ).collectAsStateWithLifecycle(initialValue = null)
    val scope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = match.description) },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Navigate up"
                        )
                    }
                }
            )
        },
    ) { padding ->
        MatchDetail(
            match = match,
            modifier = Modifier.padding(padding),
            hasReminder = reminder != null,
            allowRemindersAfter = Date()
        ) {
            scope.launch {
                if (reminder == null) {
                    createReminder(context, service, match)
                } else {
                    deleteReminder(context, service, reminder!!)
                }
            }
        }
    }
}

private suspend fun createReminder(context: Context, service: ReminderService, match: Match) {
    Reminder(0, match.date, match.description, match.home, match.away).let {
        service.insert(it)
    }.let { id ->
        Intent(context.applicationContext, ReminderBroadcastReceiver::class.java).apply {
            action = "dev.csandiego.zuju.technicalassessment.action.REMIND"
            putExtra(EXTRA_REMINDER_ID, id)
        }.let {
            PendingIntent.getBroadcast(
                context.applicationContext,
                id.toInt(),
                it,
                PendingIntent.FLAG_IMMUTABLE
            )
        }
    }.let {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.set(AlarmManager.RTC_WAKEUP, match.dateTime.time - 60000 * 15, it)
    }
}

private suspend fun deleteReminder(context: Context, service: ReminderService, reminder: Reminder) {
    service.delete(reminder)
    Intent(context.applicationContext, ReminderBroadcastReceiver::class.java).apply {
        action = "dev.csandiego.zuju.technicalassessment.action.REMIND"
        putExtra(EXTRA_REMINDER_ID, reminder.id)
    }.let {
        PendingIntent.getBroadcast(
            context.applicationContext,
            reminder.id.toInt(),
            it,
            PendingIntent.FLAG_NO_CREATE or PendingIntent.FLAG_IMMUTABLE
        )
    }?.let {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.cancel(it)
    }
}
