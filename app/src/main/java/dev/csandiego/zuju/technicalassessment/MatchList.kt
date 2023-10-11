package dev.csandiego.zuju.technicalassessment

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import dev.csandiego.zuju.technicalassessment.data.Match
import dev.csandiego.zuju.technicalassessment.data.MatchList
import dev.csandiego.zuju.technicalassessment.ui.theme.TechnicalAssessmentTheme

@Composable
fun MatchListUi(list: MatchList) {
    LazyColumn {
        item {
            Text(text = "Previous")
        }
        items(list.previous) {
            Text(text = it.description)
        }
        item {
            Text(text = "Upcoming")
        }
        items(list.upcoming) {
            Text(text = it.description)
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
        MatchListUi(list = list)
    }
}