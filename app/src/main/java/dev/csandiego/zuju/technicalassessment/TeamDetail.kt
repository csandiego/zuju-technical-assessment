package dev.csandiego.zuju.technicalassessment

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dev.csandiego.zuju.technicalassessment.data.Match
import dev.csandiego.zuju.technicalassessment.data.MatchList
import dev.csandiego.zuju.technicalassessment.data.Team
import dev.csandiego.zuju.technicalassessment.ui.theme.TechnicalAssessmentTheme

@Composable
fun TeamDetail(team: Team, matches: MatchList, modifier: Modifier = Modifier, onClick: (Match) -> Unit) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(text = team.id)
        Text(text = team.name)
        Text(text = team.logo)
        MatchListUi(list = matches, onClick = onClick)
    }
}

@Preview(showBackground = true)
@Composable
fun TeamDetailPreview() {
    val team = Team(
        "767ec50c-7fdb-4c3d-98f9-d6727ef8252b",
        "Team Red Dragons",
        "https://tstzj.s3.amazonaws.com/dragons.png"
    )
    val matches = MatchList(
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
        TeamDetail(team = team, matches = matches) {
        }
    }
}