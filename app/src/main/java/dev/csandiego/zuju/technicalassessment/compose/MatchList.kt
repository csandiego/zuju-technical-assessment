package dev.csandiego.zuju.technicalassessment.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.csandiego.zuju.technicalassessment.R
import dev.csandiego.zuju.technicalassessment.data.Match
import dev.csandiego.zuju.technicalassessment.data.MatchList
import dev.csandiego.zuju.technicalassessment.ui.theme.TechnicalAssessmentTheme
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun MatchRow(match: Match, onClick: (Match) -> Unit) {
    Column {
        Column(modifier = Modifier
            .padding(16.dp, 8.dp)
            .clickable { onClick(match) }) {
            Text(text = match.description, fontSize = 18.sp)
            Text(
                text = SimpleDateFormat(
                    "d LLLL yyyy h:mm a",
                    Locale.ENGLISH
                ).format(match.dateTime), fontSize = 14.sp
            )

        }
        Divider()
    }
}

@Composable
fun MatchListUi(list: MatchList, onClick: (Match) -> Unit) {
    LazyColumn {
        item {
            Text(
                text = stringResource(id = R.string.previous),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(16.dp, 8.dp)
            )
        }
        items(list.previous) {
            MatchRow(match = it, onClick = onClick)
        }
        item {
            Text(
                text = stringResource(id = R.string.upcoming),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(16.dp, 8.dp)
            )
        }
        items(list.upcoming) {
            MatchRow(match = it, onClick = onClick)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MatchListUiPreview() {
    val list = MatchList(
        listOf(
            Match(
                "2022-04-23T18:00:00.000Z",
                "Team Cool Eagles vs. Team Red Dragons",
                "Team Cool Eagles",
                "Team Red Dragons",
                "Team Red Dragons",
                "https://tstzj.s3.amazonaws.com/highlights.mp4"
            ),
            Match(
                "2022-04-24T18:00:00.000Z",
                "Team Chill Elephants vs. Team Royal Knights",
                "Team Chill Elephants",
                "Team Royal Knights",
                "Team Chill Elephants",
                "https://tstzj.s3.amazonaws.com/highlights.mp4"
            ),
            Match(
                "2022-04-24T18:00:00.000Z",
                "Team Win Kings vs. Team Growling Tigers",
                "Team Win Kings",
                "Team Growling Tigers",
                "Team Win Kings",
                "https://tstzj.s3.amazonaws.com/highlights.mp4"
            )
        ),
        listOf(
            Match(
                "2024-08-13T20:00:00.000Z",
                "Team Cool Eagles vs. Team Serious Lions",
                "Team Cool Eagles",
                "Team Serious Lions"
            ),
            Match(
                "2024-08-13T20:00:00.000Z",
                "Team Angry Pandas vs. Team Win Kings",
                "Team Angry Pandas",
                "Team Win Kings"
            )
        )
    )
    TechnicalAssessmentTheme {
        MatchListUi(list = list) {
        }
    }
}