package dev.csandiego.zuju.technicalassessment

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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import dev.csandiego.zuju.technicalassessment.data.Match
import dev.csandiego.zuju.technicalassessment.data.Reminder
import dev.csandiego.zuju.technicalassessment.service.ReminderService
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MatchDetailScreen(navController: NavHostController, service: ReminderService, match: Match) {
    val reminders by service.getAllAsFlow().collectAsStateWithLifecycle(initialValue = emptyList())
    val reminder = reminders.find { it.date == match.date && it.description == match.description }
    val scope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = match.description) },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Navigate up")
                    }
                }
            )
        },
    ) { padding ->
        MatchDetail(match = match, modifier = Modifier.padding(padding), willBeReminded = reminder != null) {
            scope.launch {
                if (reminder == null) {
                    service.insert(Reminder(0, match.date, match.description, match.home, match.away))
                } else {
                    service.delete(reminder)
                }
            }
        }
    }
}