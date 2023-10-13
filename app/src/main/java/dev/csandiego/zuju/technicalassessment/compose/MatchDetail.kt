package dev.csandiego.zuju.technicalassessment.compose

import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dev.csandiego.zuju.technicalassessment.data.Match
import dev.csandiego.zuju.technicalassessment.ui.theme.TechnicalAssessmentTheme

@Composable
fun MatchDetail(
    match: Match,
    modifier: Modifier = Modifier,
    willBeReminded: Boolean = false,
    notificationEnabled: Boolean = false,
    onAllowNotification: () -> Unit = {},
    onClick: (Match) -> Unit = {}
) {
    Column(modifier = modifier.fillMaxWidth()) {
        match.highlights.takeIf { !it.isNullOrBlank() && it != "null" }?.let {
            Video(uri = Uri.parse(it))
        }
        Text(text = match.date)
        Text(text = match.description)
        Text(text = match.home)
        Text(text = match.away)
        match.winner.takeIf { !it.isNullOrBlank() && it != "null" }?.let {
            Text(text = it)
        }
        Button(onClick = { onClick(match) }) {
            Text(text = if (willBeReminded) "Don't remind" else "Remind")
        }
        if (!notificationEnabled) {
            Button(onClick = { onAllowNotification() }) {
                Text(text = "Allow Notification")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MatchDetailPreview() {
    val match = Match(
        "2022-04-23T18:00:00.000Z",
        "Team Cool Eagles vs. Team Red Dragons",
        "Team Cool Eagles",
        "Team Red Dragons",
        "Team Red Dragons",
        "https://tstzj.s3.amazonaws.com/highlights.mp4"
    )
    TechnicalAssessmentTheme {
        MatchDetail(match = match)
    }
}