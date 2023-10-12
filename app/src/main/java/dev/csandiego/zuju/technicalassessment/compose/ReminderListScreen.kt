package dev.csandiego.zuju.technicalassessment.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.csandiego.zuju.technicalassessment.service.ReminderService
import kotlinx.coroutines.launch

@Composable
fun ReminderListScreen(service: ReminderService, modifier: Modifier = Modifier) {
    val scope = rememberCoroutineScope()
    val reminders by service.getAllAsFlow().collectAsStateWithLifecycle(initialValue = emptyList())
    ReminderList(reminders = reminders, modifier = modifier) {
        scope.launch {
            service.delete(reminder = it)
        }
    }
}