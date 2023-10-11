package dev.csandiego.zuju.technicalassessment

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dev.csandiego.zuju.technicalassessment.data.Match
import dev.csandiego.zuju.technicalassessment.ui.theme.TechnicalAssessmentTheme

@Composable
fun MatchDetail(match: Match) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = match.date)
        Text(text = match.description)
        Text(text = match.home)
        Text(text = match.away)
        Text(text = match.winner ?: "")
        Text(text = match.highlights ?: "")
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