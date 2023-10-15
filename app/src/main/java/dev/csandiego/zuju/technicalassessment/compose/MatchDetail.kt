package dev.csandiego.zuju.technicalassessment.compose

import android.net.Uri
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.csandiego.zuju.technicalassessment.R
import dev.csandiego.zuju.technicalassessment.data.Match
import dev.csandiego.zuju.technicalassessment.ui.theme.TechnicalAssessmentTheme
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun MatchDetail(
    match: Match,
    modifier: Modifier = Modifier,
    hasReminder: Boolean = false,
    allowRemindersAfter: Date = Date(),
    onClick: (Match) -> Unit = {}
) {
    val fmt = SimpleDateFormat("d LLLL yyyy h:mm a", Locale.ENGLISH)
    Column(modifier = modifier.fillMaxWidth()) {
        match.highlights?.let {
            Video(uri = Uri.parse(it))
        }
        Text(
            text = fmt.format(match.dateTime),
            fontSize = 18.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
        match.winner?.let {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.winner).uppercase(),
                    fontSize = 14.sp,
                    modifier = Modifier.align(CenterHorizontally)
                )
                Text(
                    text = it,
                    fontSize = 32.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = stringResource(id = R.string.home).uppercase(),
                    fontSize = 12.sp,
                    modifier = Modifier.align(CenterHorizontally)
                )
                Text(
                    text = match.home,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = stringResource(id = R.string.away).uppercase(),
                    fontSize = 12.sp,
                    modifier = Modifier.align(CenterHorizontally)
                )
                Text(
                    text = match.away,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
        Box(modifier = Modifier.weight(1f))
        if (allowRemindersAfter < match.dateTime) {
            Button(
                onClick = { onClick(match) }, modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, end = 16.dp, bottom = 16.dp, start = 16.dp)
            ) {
                if (hasReminder) {
                    R.string.cancel_reminder
                } else {
                    R.string.remind_me
                }.let { Text(text = stringResource(id = it).uppercase(), fontSize = 18.sp) }
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