package dev.csandiego.zuju.technicalassessment

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dev.csandiego.zuju.technicalassessment.data.Reminder

@Composable
fun ReminderList(reminders: List<Reminder>, modifier: Modifier = Modifier, onClick: (Reminder) -> Unit = {}) {
    LazyColumn(modifier = modifier.fillMaxWidth()) {
        items(reminders) {
            Text(text = it.description, modifier = modifier.clickable { onClick(it) })
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ReminderListPreview() {
    val reminders = listOf(
        Reminder(
            1,
            "2022-04-23T18:00:00.000Z",
            "Team Cool Eagles vs. Team Red Dragons",
            "Team Cool Eagles",
            "Team Red Dragons"
        ),
        Reminder(
            2,
            "2022-04-24T18:00:00.000Z",
            "Team Chill Elephants vs. Team Royal Knights",
            "Team Chill Elephants",
            "Team Royal Knights"
        ),
        Reminder(
            3,
            "2022-04-24T18:00:00.000Z",
            "Team Win Kings vs. Team Growling Tigers",
            "Team Win Kings",
            "Team Growling Tigers"
        )
    )
    ReminderList(reminders = reminders)
}